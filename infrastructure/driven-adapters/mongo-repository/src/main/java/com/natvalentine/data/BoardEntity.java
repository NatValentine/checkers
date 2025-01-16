package com.natvalentine.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "boards")
public class BoardEntity {
    @Id
    private String id;

    private ArrayList<TileEntity> tiles;

    public BoardEntity() {}

    public BoardEntity(String id, ArrayList<TileEntity> tiles) {
        this.id = id;
        this.tiles = tiles;
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
}
