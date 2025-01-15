package com.natvalentine.database.checkers;

import com.natvalentine.data.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IUserMongoRepository extends ReactiveMongoRepository<UserEntity, String> {
    Mono<UserEntity> findByUsername(String username);
}
