package com.natvalentine.user.queries.usecases;

import com.natvalentine.gateway.IUserRepository;
import com.natvalentine.generics.interfaces.IUseCaseEmptyGet;
import com.natvalentine.generics.utils.QueryResponse;
import com.natvalentine.user.queries.responses.UserResponse;
import reactor.core.publisher.Mono;

public class GetAllUsersViewUseCase implements IUseCaseEmptyGet<UserResponse> {
    private final IUserRepository userRepository;

    public GetAllUsersViewUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<QueryResponse<UserResponse>> get() {
        return userRepository.getAll()
                .map(userDTO -> new UserResponse(
                        userDTO.getId(),
                        userDTO.getUsername()
                ))
                .collectList()
                .flatMap(users -> Mono.just(QueryResponse.ofMultiple(users)));
    }
}
