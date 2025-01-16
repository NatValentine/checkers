package com.natvalentine.board.queries.responses;

public record PieceResponse(String id, String type, Boolean isActive, String color, Integer startingLocationX, Integer startingLocationY) {
}
