package com.natvalentine.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.natvalentine.database.events",
        reactiveMongoTemplateRef = "eventsMongoTemplate")
public class EventMongoConfig {
    @Value("${spring.data.mongodb.events-uri}")
    private String eventsMongoUri;

    @Bean(name = "eventsDatabaseFactory")
    public ReactiveMongoDatabaseFactory eventsDatabaseFactory() {
        MongoClient mongoClient = MongoClients.create(eventsMongoUri);
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, "events");
    }

    @Bean(name = "eventsMongoTemplate")
    public ReactiveMongoTemplate eventsMongoTemplate(@Qualifier("eventsDatabaseFactory") ReactiveMongoDatabaseFactory eventsDatabaseFactory) {
        return new ReactiveMongoTemplate(eventsDatabaseFactory);
    }
}
