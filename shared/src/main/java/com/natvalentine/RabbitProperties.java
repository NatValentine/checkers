package com.natvalentine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:rabbit-config.properties")
public class RabbitProperties {
    @Value("${user.created.exchange}")
    private String userExchange;

    @Value("${user.created.queue}")
    private String userQueue;

    @Value("${user.created.routingKey}")
    private String userRoutingKey;

    @Value("${board.created.exchange}")
    private String boardCreatedExchange;

    @Value("${board.created.queue}")
    private String boardCreatedQueue;

    @Value("${board.created.routingKey}")
    private String boardCreatedRoutingKey;

    @Value("${board.updated.exchange}")
    private String boardUpdatedExchange;

    @Value("${board.updated.queue}")
    private String boardUpdatedQueue;

    @Value("${board.updated.routingKey}")
    private String boardUpdatedRoutingKey;

    @Value("${player.created.exchange}")
    private String playerCreatedExchange;

    @Value("${player.created.queue}")
    private String playerCreatedQueue;

    @Value("${player.created.routingKey}")
    private String playerCreatedRoutingKey;

    public String getUserExchange() {
        return userExchange;
    }

    public String getUserQueue() {
        return userQueue;
    }

    public String getUserRoutingKey() {
        return userRoutingKey;
    }

    public String getBoardCreatedExchange() {
        return boardCreatedExchange;
    }

    public String getBoardCreatedQueue() {
        return boardCreatedQueue;
    }

    public String getBoardCreatedRoutingKey() {
        return boardCreatedRoutingKey;
    }

    public String getBoardUpdatedExchange() {
        return boardUpdatedExchange;
    }

    public String getBoardUpdatedQueue() {
        return boardUpdatedQueue;
    }

    public String getBoardUpdatedRoutingKey() {
        return boardUpdatedRoutingKey;
    }

    public String getPlayerCreatedExchange() { return playerCreatedExchange; }

    public String getPlayerCreatedQueue() {
        return playerCreatedQueue;
    }

    public String getPlayerCreatedRoutingKey() {
        return playerCreatedRoutingKey;
    }
}
