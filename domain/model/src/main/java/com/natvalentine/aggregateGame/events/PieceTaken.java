package com.natvalentine.aggregateGame.events;

import com.natvalentine.generics.domain.DomainEvent;

public class PieceTaken extends DomainEvent {
    public PieceTaken(String pieceId) {
        super(GameEventsEnum.PIECE_TAKEN.name());
        // kill piece with id pieceId
        // dont actually kill it set isActive to false
    }
}
