package com.natvalentine.gateway.dto;

public class TileDTO {
    private Integer locationX;
    private Integer locationY;
    private PieceDTO piece;

    public TileDTO(Integer locationX, Integer locationY, PieceDTO piece) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.piece = piece;
    }

    public Integer getLocationX() {
        return locationX;
    }

    public void setLocationX(Integer locationX) {
        this.locationX = locationX;
    }

    public Integer getLocationY() {
        return locationY;
    }

    public void setLocationY(Integer locationY) {
        this.locationY = locationY;
    }

    public PieceDTO getPiece() {
        return piece;
    }

    public void setPiece(PieceDTO piece) {
        this.piece = piece;
    }
}
