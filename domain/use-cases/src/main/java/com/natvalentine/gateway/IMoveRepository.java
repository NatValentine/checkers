package com.natvalentine.gateway;

import com.natvalentine.gateway.dto.MoveDTO;
import reactor.core.publisher.Mono;

public interface IMoveRepository {
    Mono<MoveDTO> save(MoveDTO move);
    Mono<MoveDTO> getOne(String id);
}
