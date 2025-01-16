package com.natvalentine.database.checkers;

import com.natvalentine.data.PlayerEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IPlayerMongoRepository extends ReactiveMongoRepository<PlayerEntity, String> {

}
