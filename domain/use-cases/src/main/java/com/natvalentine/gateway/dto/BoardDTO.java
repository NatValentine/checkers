package com.natvalentine.gateway.dto;

import java.util.ArrayList;

public class BoardDTO {
    private String id;
    private ArrayList<TileDTO> tiles;
    private ArrayList<PieceDTO> pieces;

    public BoardDTO(String id, ArrayList<TileDTO> tiles, ArrayList<PieceDTO> pieces) {
        this.id = id;
        this.tiles = tiles;
        this.pieces = pieces;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<TileDTO> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<TileDTO> tiles) {
        this.tiles = tiles;
    }

    public ArrayList<PieceDTO> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<PieceDTO> pieces) {
        this.pieces = pieces;
    }
}
