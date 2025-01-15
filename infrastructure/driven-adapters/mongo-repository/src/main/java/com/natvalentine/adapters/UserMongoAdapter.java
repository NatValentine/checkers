package com.natvalentine.adapters;

import com.natvalentine.data.UserEntity;
import com.natvalentine.database.checkers.IUserMongoRepository;
import com.natvalentine.gateway.IUserRepository;
import com.natvalentine.gateway.dto.UserDTO;
import com.natvalentine.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserMongoAdapter implements IUserRepository {
    private final IUserMongoRepository mongoRepository;
    private final ReactiveMongoTemplate checkersMongoTemplate;

    public UserMongoAdapter(IUserMongoRepository mongoRepository, @Qualifier("checkersMongoTemplate") ReactiveMongoTemplate checkersMongoTemplate) {
        this.mongoRepository = mongoRepository;
        this.checkersMongoTemplate = checkersMongoTemplate;
    }

    @Override
    public Mono<UserDTO> save(UserDTO user) {
        UserEntity userEntity = UserEntityMapper.toEntity(user);
        return mongoRepository.save(userEntity).map(UserEntityMapper::fromEntity);
    }

    @Override
    public Mono<UserDTO> findByUsername(String username) {
        return mongoRepository.findByUsername(username).map(UserEntityMapper::fromEntity);
    }

    @Override
    public Flux<UserDTO> getAll() {
        return mongoRepository.findAll().map(UserEntityMapper::fromEntity);
    }
}
