package com.natvalentine.gateway.dto;

public class MoveDTO {
    private String pieceId;
    private String destinationTileId;
    private String pieceTakenId;

    public MoveDTO(String pieceId, String destinationTileId, String pieceTakenId) {
        this.pieceId = pieceId;
        this.destinationTileId = destinationTileId;
        this.pieceTakenId = pieceTakenId;
    }

    public String getPieceId() {
        return pieceId;
    }

    public void setPieceId(String pieceId) {
        this.pieceId = pieceId;
    }

    public String getDestinationTileId() {
        return destinationTileId;
    }

    public void setDestinationTileId(String destinationTileId) {
        this.destinationTileId = destinationTileId;
    }

    public String getPieceTakenId() {
        return pieceTakenId;
    }

    public void setPieceTakenId(String pieceTakenId) {
        this.pieceTakenId = pieceTakenId;
    }
}
