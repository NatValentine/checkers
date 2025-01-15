package com.natvalentine.gateway.dto;

public class PlayerDTO {
    private String id;
    private String color;
    private Boolean isCurrent;

    public PlayerDTO(String id, String color, Boolean isCurrent) {
        this.id = id;
        this.color = color;
        this.isCurrent = isCurrent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
