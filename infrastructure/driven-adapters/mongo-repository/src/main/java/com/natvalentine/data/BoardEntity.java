package com.natvalentine.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document(collection = "boards")
public class BoardEntity {
    @Id
    private String id;

    private ArrayList<TileEntity> tiles;

    @Indexed
    private String gameId;

    public BoardEntity() {}

    public BoardEntity(String id, ArrayList<TileEntity> tiles, String gameId) {
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

    public ArrayList<TileEntity> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<TileEntity> tiles) {
        this.tiles = tiles;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
