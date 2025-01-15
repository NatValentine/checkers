package com.natvalentine.user.commands.usecases;

import com.natvalentine.ValidationException;
import com.natvalentine.aggregate.Game;
import com.natvalentine.gateway.IBusEvent;
import com.natvalentine.gateway.IEventStore;
import com.natvalentine.generics.interfaces.IUseCaseExecute;
import com.natvalentine.user.commands.CreateUserCommand;
import com.natvalentine.user.queries.query.GetUserByUsernameQuery;
import com.natvalentine.user.queries.responses.UserResponse;
import com.natvalentine.user.queries.usecases.GetUserByUsernameViewUseCase;
import reactor.core.publisher.Mono;

public class CreateUserUseCase implements IUseCaseExecute<CreateUserCommand, UserResponse> {
    private final IEventStore eventRepository;
    private final IBusEvent busEvent;
    private final GetUserByUsernameViewUseCase getUserByUsernameViewUseCase;

    public CreateUserUseCase(IEventStore eventRepository, IBusEvent busEvent, GetUserByUsernameViewUseCase getUserByUsernameViewUseCase) {
        this.eventRepository = eventRepository;
        this.busEvent = busEvent;
        this.getUserByUsernameViewUseCase = getUserByUsernameViewUseCase;
    }

    @Override
    public Mono<UserResponse> execute(CreateUserCommand request) {
        return getUserByUsernameViewUseCase.get(new GetUserByUsernameQuery(request.getUsername()))
                .flatMap(existingUser -> Mono.<UserResponse>error(new ValidationException("Username already exists")))
                .switchIfEmpty(Mono.defer(() -> {
                    Game game = new Game();
                    game.createUser(request.getUsername());

                    game.getUncommittedEvents()
                            .stream()
                            .map(eventRepository::save)
                            .forEach(busEvent::sendEventUserCreated);

                    game.markEventsAsCommitted();

                    return Mono.just(new UserResponse(
                            game.getUser().getId().getValue(),
                            game.getUser().getUsername().getValue()
                    ));
                }));
    }
}
