package com.natvalentine.gateway.dto;

public class PlayerDTO {
    private String id;
    private String userId;
    private String gameId;
    private String color;
    private Boolean isCurrent;

    public PlayerDTO(String id, String userId, String gameId, String color, Boolean isCurrent) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.color = color;
        this.isCurrent = isCurrent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }
}
