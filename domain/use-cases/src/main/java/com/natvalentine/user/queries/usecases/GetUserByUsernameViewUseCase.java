package com.natvalentine.user.queries.usecases;

import com.natvalentine.gateway.IUserRepository;
import com.natvalentine.gateway.dto.UserDTO;
import com.natvalentine.user.queries.query.GetUserByUsernameQuery;
import reactor.core.publisher.Mono;

public class GetUserByUsernameViewUseCase {
    private final IUserRepository userRepository;

    public GetUserByUsernameViewUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserDTO> get(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.getUsername()).switchIfEmpty(Mono.empty());
    }
}
