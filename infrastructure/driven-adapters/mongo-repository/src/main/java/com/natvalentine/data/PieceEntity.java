package com.natvalentine.data;

import com.natvalentine.gateway.dto.TileDTO;

public class PieceEntity {
    private String id;
    private String type;
    private Boolean isActive;
    private String color;
    private TileEntity startingLocation;

    public PieceEntity(String id, String type, Boolean isActive, String color, TileEntity startingLocation) {
        this.id = id;
        this.type = type;
        this.isActive = isActive;
        this.color = color;
        this.startingLocation = startingLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TileEntity getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(TileEntity startingLocation) {
        this.startingLocation = startingLocation;
    }
}