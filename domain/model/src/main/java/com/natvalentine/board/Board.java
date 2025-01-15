package com.natvalentine.board;

import com.natvalentine.board.values.BoardId;
import com.natvalentine.generics.utils.Entity;
import com.natvalentine.piece.Piece;

import java.util.ArrayList;

public class Board extends Entity<BoardId> {
    private ArrayList<Tile> tiles;

    public Board(BoardId id) {
        super(id);
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
