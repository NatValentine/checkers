package com.natvalentine.move;

import com.natvalentine.board.Tile;
import com.natvalentine.generics.utils.Entity;
import com.natvalentine.move.values.MoveId;
import com.natvalentine.piece.Piece;

public class Move extends Entity<MoveId> {
    private final Piece piece;
    private final Tile destinationTile;
    private final Piece pieceTaken;

    public Move(MoveId id, Piece piece, Tile destinationTile, Piece pieceTaken) {
        super(id);
        this.piece = piece;
        this.destinationTile = destinationTile;
        this.pieceTaken = pieceTaken;
    }

    public Piece getPiece() {
        return piece;
    }

    public Tile getDestinationTile() {
        return destinationTile;
    }

    public Piece getPieceTaken() {
        return pieceTaken;
    }
}
