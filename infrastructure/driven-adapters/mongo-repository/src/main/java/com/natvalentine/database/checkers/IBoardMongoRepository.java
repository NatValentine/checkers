package com.natvalentine.database.checkers;

import com.natvalentine.data.BoardEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IBoardMongoRepository extends ReactiveMongoRepository<BoardEntity, String> {
    Mono<BoardEntity> findById(String id);
}
