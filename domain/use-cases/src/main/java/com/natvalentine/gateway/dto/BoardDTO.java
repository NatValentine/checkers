package com.natvalentine.gateway.dto;

import java.util.ArrayList;

public class BoardDTO {
    private String id;
    private ArrayList<TileDTO> tiles;
    private String gameId;

    public BoardDTO(String id, ArrayList<TileDTO> tiles, String gameId) {
        this.id = id;
        this.tiles = tiles;
        this.gameId = gameId;
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
