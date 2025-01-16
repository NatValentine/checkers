package com.natvalentine.aggregateGame.events;

import com.natvalentine.generics.domain.DomainEvent;

public class PlayerCreated extends DomainEvent {
    private final String userId;
    private final String playerId;
    private final String color;
    private final Boolean current;

    public PlayerCreated(String userId, String playerId, String color, Boolean current) {
        super(GameEventsEnum.PLAYER_CREATED.name());
        this.userId = userId;
        this.playerId = playerId;
        this.color = color;
        this.current = current;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getColor() {
        return color;
    }

    public Boolean getCurrent() {
        return current;
    }
}
