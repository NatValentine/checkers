package com.natvalentine.data;

import org.springframework.data.mongodb.core.mapping.Field;

public class TileEntity {
    @Field
    private int x;

    @Field
    private int y;

    private PieceEntity  piece;

    public TileEntity(int x, int y, PieceEntity piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PieceEntity getPiece() {
        return piece;
    }

    public void setPiece(PieceEntity piece) {
        this.piece = piece;
    }
}