package com.natvalentine.gateway;

import com.natvalentine.gateway.dto.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserRepository {
    Mono<UserDTO> save(UserDTO user);
    Mono<UserDTO> findByUsername(String username);
    Flux<UserDTO> getAll();
}
