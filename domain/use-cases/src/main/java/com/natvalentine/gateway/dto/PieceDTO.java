package com.natvalentine.gateway.dto;

public class PieceDTO {
    private String id;
    private Boolean isActive;
    private String type;
    private String playerId;
    private TileDTO startingLocation;
    private TileDTO currentLocation;

    public PieceDTO(String id, Boolean isActive, String type, String playerId, TileDTO startingLocation, TileDTO currentLocation) {
        this.id = id;
        this.isActive = isActive;
        this.type = type;
        this.playerId = playerId;
        this.startingLocation = startingLocation;
        this.currentLocation = currentLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public TileDTO getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(TileDTO startingLocation) {
        this.startingLocation = startingLocation;
    }

    public TileDTO getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(TileDTO currentLocation) {
        this.currentLocation = currentLocation;
    }
}
