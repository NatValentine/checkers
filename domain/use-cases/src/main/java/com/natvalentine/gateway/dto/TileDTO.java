package com.natvalentine.gateway.dto;

public class TileDTO {
    private Integer x;
    private Integer y;
    private PieceDTO piece;

    public TileDTO(Integer x, Integer y, PieceDTO piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public PieceDTO getPiece() {
        return piece;
    }

    public void setPiece(PieceDTO piece) {
        this.piece = piece;
    }
}
