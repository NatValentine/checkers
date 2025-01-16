package com.natvalentine.data;

import java.util.ArrayList;

public class BoardResponseDTO {
    private String id;
    private String gameId;
    private ArrayList<TileResponseDTO> tiles;

    public BoardResponseDTO(String id, String gameId, ArrayList<TileResponseDTO> tiles) {
        this.id = id;
        this.gameId = gameId;
        this.tiles = tiles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public ArrayList<TileResponseDTO> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<TileResponseDTO> tiles) {
        this.tiles = tiles;
    }
}
