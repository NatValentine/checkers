package com.natvalentine.gateway;

import com.natvalentine.gateway.dto.PlayerDTO;
import reactor.core.publisher.Mono;

public interface IPlayerRepository {
    Mono<PlayerDTO> save(PlayerDTO player);
    Mono<PlayerDTO> getOne(String id);
    Mono<PlayerDTO> update(PlayerDTO player);
}
