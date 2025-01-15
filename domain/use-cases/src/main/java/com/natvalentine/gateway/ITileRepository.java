package com.natvalentine.gateway;

import com.natvalentine.gateway.dto.TileDTO;
import reactor.core.publisher.Mono;

public interface ITileRepository {
    Mono<TileDTO> save(TileDTO tile);
    Mono<TileDTO> update(TileDTO tile);
    Mono<TileDTO> getOne(Integer x, Integer y);
}
