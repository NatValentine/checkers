package com.natvalentine.aggregate.events;

import com.natvalentine.generics.domain.DomainEvent;

public class GameOver extends DomainEvent {
    public GameOver() {
        super(EventsEnum.GAME_OVER.name());
        // update status to finished, result to winner or stalemate
    }
}
