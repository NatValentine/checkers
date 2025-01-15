package com.natvalentine.board;

import com.natvalentine.board.values.Location;
import com.natvalentine.generics.utils.ColorsEnum;
import com.natvalentine.piece.Piece;

public class Tile {
    private final Location location;
    private Piece piece;

    public Tile(int x, int y) {
        this.location = Location.of(x, y);
        this.piece = null;
    }

    public Location getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public ColorsEnum getColor() {
        int coordsSum = this.location.getValue().x() + this.location.getValue().y();
        return coordsSum % 2 == 0 ? ColorsEnum.BLACK : ColorsEnum.WHITE;
    }

    public Boolean isEmpty() {
        return piece == null;
    }

    @Override
    public String toString() {
        if(!this.isEmpty()) {
            return this.piece.toString();
        }
        return " -";
    }
}
