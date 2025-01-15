package com.natvalentine.aggregate.events;

import com.natvalentine.generics.domain.DomainEvent;

public class PieceTaken extends DomainEvent {
    public PieceTaken(String pieceId) {
        super(EventsEnum.PIECE_TAKEN.name());
        // kill piece with id pieceId
        // dont actually kill it set isActive to false
    }
}
