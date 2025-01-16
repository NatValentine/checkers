package com.natvalentine.adapters;

import com.natvalentine.data.PlayerEntity;
import com.natvalentine.database.checkers.IPlayerMongoRepository;
import com.natvalentine.gateway.IPlayerRepository;
import com.natvalentine.gateway.dto.PlayerDTO;
import com.natvalentine.mapper.PlayerEntityMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PlayerMongoAdapter implements IPlayerRepository {
    private final IPlayerMongoRepository mongoRepository;
    private final ReactiveMongoTemplate checkersMongoTemplate;

    public PlayerMongoAdapter(IPlayerMongoRepository mongoRepository, @Qualifier("checkersMongoTemplate") ReactiveMongoTemplate checkersMongoTemplate) {
        this.mongoRepository = mongoRepository;
        this.checkersMongoTemplate = checkersMongoTemplate;
    }

    @Override
    public Mono<PlayerDTO> save(PlayerDTO player) {
        PlayerEntity playerEntity = PlayerEntityMapper.toEntity(player);
        return mongoRepository.save(playerEntity).map(PlayerEntityMapper::fromEntity);
    }

    @Override
    public Mono<PlayerDTO> getOne(String id) {
        return null;
    }

    @Override
    public Mono<PlayerDTO> update(PlayerDTO player) {
        return null;
    }
}
