package com.natvalentine.aggregateGame.events;

import com.natvalentine.generics.domain.DomainEvent;

public class PlayerCreated extends DomainEvent {
    private final String userId;
    private final String playerId;

    public PlayerCreated(String userId, String playerId) {
        super(GameEventsEnum.PLAYER_CREATED.name());
        this.userId = userId;
        this.playerId = playerId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlayerId() {
        return playerId;
    }
}