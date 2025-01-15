package com.natvalentine.gateway;

import com.natvalentine.gateway.dto.PieceDTO;
import reactor.core.publisher.Mono;

public interface IPieceRepository {
    Mono<PieceDTO> save(PieceDTO piece);
    Mono<PieceDTO> getOne(String id);
    Mono<PieceDTO> update(PieceDTO piece);
}
