package com.natvalentine.gateway;

import com.natvalentine.generics.domain.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IEventStore {
    Mono<DomainEvent> save(DomainEvent event);
    Flux<DomainEvent> findAggregate(String aggregateId);
    Flux<DomainEvent> findAllAggregates();
}
