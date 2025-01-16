package com.natvalentine.aggregateGame.events;

import com.natvalentine.board.Tile;
import com.natvalentine.generics.domain.DomainEvent;

import java.util.ArrayList;

public class BoardCreated extends DomainEvent {
    private final String id;
    private final ArrayList<Tile> tiles;

    public BoardCreated(String id, ArrayList<Tile> tiles) {
        super(GameEventsEnum.BOARD_CREATED.name());
        this.id = id;
        this.tiles = tiles;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
