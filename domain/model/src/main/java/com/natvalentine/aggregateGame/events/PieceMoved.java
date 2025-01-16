package com.natvalentine.aggregateGame.events;

import com.natvalentine.board.Tile;
import com.natvalentine.generics.domain.DomainEvent;

public class PieceMoved extends DomainEvent {
    private final String id;
    private final Tile from;
    private final Tile to;

    public PieceMoved(String id, Tile from, Tile to) {
        super(GameEventsEnum.PIECE_MOVED.name());
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public Tile getFrom() {
        return from;
    }

    public Tile getTo() {
        return to;
    }
}
