package com.natvalentine.gateway.dto;

public class PieceDTO {
    private String id;
    private String type;
    private Boolean isActive;
    private String color;
    private TileDTO startingLocation;

    public PieceDTO(String id, String type, Boolean isActive, String color, TileDTO startingLocation) {
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

    public TileDTO getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(TileDTO startingLocation) {
        this.startingLocation = startingLocation;
    }
}
