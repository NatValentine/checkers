package com.natvalentine.gateway;

import com.natvalentine.gateway.dto.BoardDTO;
import reactor.core.publisher.Mono;

public interface IBoardRepository {
    Mono<BoardDTO> save(BoardDTO board);
    Mono<BoardDTO> getOne(String id);
    Mono<BoardDTO> update(BoardDTO board);
}
