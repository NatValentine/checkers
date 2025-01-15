package com.natvalentine.database.events;

import com.natvalentine.data.EventEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IEventMongoRepository extends ReactiveMongoRepository<EventEntity, String> {
    Flux<EventEntity> findByAggregateId(String aggregateId);
}
