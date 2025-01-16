package com.natvalentine.aggregateGame.events;

import com.natvalentine.board.Tile;
import com.natvalentine.generics.domain.DomainEvent;

import java.util.ArrayList;

public class BoardCreated extends DomainEvent {
    private final String id;
    private final ArrayList<Tile> tiles;

    public BoardCreated(String id) {
        super(GameEventsEnum.BOARD_CREATED.name());
        this.id = id;
        this.tiles = this.generateBoard();
    }

    private ArrayList<Tile> generateBoard() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int y = 1; y < 9; y++) {
            for (int x = 1; x < 9; x++) {
                tiles.add(new Tile(x, y));
            }
        }
        return tiles;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
