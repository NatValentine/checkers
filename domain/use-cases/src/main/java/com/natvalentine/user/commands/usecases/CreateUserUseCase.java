package com.natvalentine.user.commands.usecases;

import com.natvalentine.JwtServiceAdapter;
import com.natvalentine.ValidationException;
import com.natvalentine.gateway.IBusEvent;
import com.natvalentine.gateway.IEventStore;
import com.natvalentine.PasswordHasher;
import com.natvalentine.generics.interfaces.IUseCaseExecute;
import com.natvalentine.user.User;
import com.natvalentine.user.commands.CreateUserCommand;
import com.natvalentine.user.events.UserCreated;
import com.natvalentine.user.queries.query.GetUserByUsernameQuery;
import com.natvalentine.user.queries.responses.UserResponse;
import com.natvalentine.user.queries.usecases.GetUserByUsernameViewUseCase;
import com.natvalentine.user.values.UserId;
import com.natvalentine.user.values.objects.Password;
import com.natvalentine.user.values.objects.Username;
import com.natvalentine.utils.RolesEnum;
import reactor.core.publisher.Mono;

public class CreateUserUseCase implements IUseCaseExecute<CreateUserCommand, UserResponse> {
    private final IEventStore eventRepository;
    private final IBusEvent busEvent;
    private final GetUserByUsernameViewUseCase getUserByUsernameViewUseCase;
    private final PasswordHasher passwordHasher;
    private final JwtServiceAdapter jwtServiceAdapter;

    public CreateUserUseCase(IEventStore eventRepository, IBusEvent busEvent, GetUserByUsernameViewUseCase getUserByUsernameViewUseCase, PasswordHasher passwordHasher, JwtServiceAdapter jwtServiceAdapter) {
        this.eventRepository = eventRepository;
        this.busEvent = busEvent;
        this.getUserByUsernameViewUseCase = getUserByUsernameViewUseCase;
        this.passwordHasher = passwordHasher;
        this.jwtServiceAdapter = jwtServiceAdapter;
    }

    @Override
    public Mono<UserResponse> execute(CreateUserCommand request) {
        String hashedPassword = passwordHasher.hashPassword(request.getPassword());

        return getUserByUsernameViewUseCase.get(new GetUserByUsernameQuery(request.getUsername()))
                .flatMap(existingUser -> Mono.<UserResponse>error(new ValidationException("Username already exists")))
                .switchIfEmpty(Mono.defer(() -> {
                     User user = new User(new UserId(), Username.of(request.getUsername()), Password.of(hashedPassword), RolesEnum.USER.name());
                     UserCreated event = new UserCreated(user.getId().getValue(), user.getUsername().getValue(), user.getPassword().getValue(), RolesEnum.USER.name());
                    return eventRepository.save(event)
                            .doOnSuccess(busEvent::sendEventUserCreated)
                            .map(savedEvent -> {
                                String token = jwtServiceAdapter.generateToken(user.getUsername().getValue(), RolesEnum.USER.name());
                                return new UserResponse(user.getId().getValue(), user.getUsername().getValue(), user.getRole(), token);
                            });
                           }));
    }
}
