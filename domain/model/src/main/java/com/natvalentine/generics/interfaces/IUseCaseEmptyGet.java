package com.natvalentine.generics.interfaces;

import com.natvalentine.generics.utils.QueryResponse;
import reactor.core.publisher.Mono;

public interface IUseCaseEmptyGet<R> {
    Mono<QueryResponse<R>> get();
}