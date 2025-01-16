package com.natvalentine.database.checkers;

import com.natvalentine.data.BoardEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IBoardRepository extends ReactiveMongoRepository<BoardEntity, String> {
    Mono<BoardEntity> findById(String id);
}
