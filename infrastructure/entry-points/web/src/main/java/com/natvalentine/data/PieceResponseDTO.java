package com.natvalentine.data;

public record PieceResponseDTO (String id, String type, Boolean isActive, String color, Integer startingLocationX, Integer startingLocationY) {
}
