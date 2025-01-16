package com.natvalentine.data;

public class PlayerResponseDTO {
    private String id;
    private String userId;
    private String color;
    private Boolean isCurrent;
    private String gameId;

    public PlayerResponseDTO(String id, String userId, String color, Boolean isCurrent, String gameId) {
        this.id = id;
        this.userId = userId;
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
