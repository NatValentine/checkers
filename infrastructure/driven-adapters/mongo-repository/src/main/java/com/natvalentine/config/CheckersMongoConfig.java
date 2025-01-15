package com.natvalentine.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.natvalentine.database.checkers",
        reactiveMongoTemplateRef = "checkersMongoTemplate")
public class CheckersMongoConfig {
    @Value("${spring.data.mongodb.checkers-uri}")
    private String checkersMongoUri;

    @Bean(name = "checkersDatabaseFactory")
    public ReactiveMongoDatabaseFactory checkersDatabaseFactory() {
        MongoClient mongoClient = MongoClients.create(checkersMongoUri);
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, "checkers");
    }

    @Primary
    @Bean(name = "checkersMongoTemplate")
    public ReactiveMongoTemplate checkersMongoTemplate(@Qualifier("checkersDatabaseFactory") ReactiveMongoDatabaseFactory checkersDatabaseFactory) {
        return new ReactiveMongoTemplate(checkersDatabaseFactory);
    }
}
