package com.natvalentine.adapters;

import com.natvalentine.JSONMap;
import com.natvalentine.data.EventEntity;
import com.natvalentine.database.events.IEventMongoRepository;
import com.natvalentine.gateway.IEventStore;
import com.natvalentine.generics.domain.DomainEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Repository
public class EventMongoAdapter implements IEventStore {

    private final IEventMongoRepository repository;
    private final JSONMap mapper;
    private final ReactiveMongoTemplate eventsMongoTemplate;

    public EventMongoAdapter(IEventMongoRepository repository, JSONMap mapper, @Qualifier("eventsMongoTemplate")ReactiveMongoTemplate eventsMongoTemplate) {
        this.repository = repository;
        this.mapper = mapper;
        this.eventsMongoTemplate = eventsMongoTemplate;
    }

    @Override
    public Mono<DomainEvent> save(DomainEvent event) {
        EventEntity e = new EventEntity(
                event.getEventId(),
                event.getAggregateRootId(),
                event.getEventType(),
                EventEntity.wrapEvent(event, mapper),
                event.getWhen().toString(),
                event.getVersion()
        );
        return repository.save(e)
                .thenReturn(event);
    }

    @Override
    public Flux<DomainEvent> findAggregate(String aggregateId) {
        return repository.findByAggregateId(aggregateId)
                .map(eventEntity -> eventEntity.deserializeEvent(mapper))
                .sort(Comparator.comparing(DomainEvent::getVersion)
                        .thenComparing(DomainEvent::getWhen));
    }

    @Override
    public Flux<DomainEvent> findAllAggregates() {
        return null;
    }
}
