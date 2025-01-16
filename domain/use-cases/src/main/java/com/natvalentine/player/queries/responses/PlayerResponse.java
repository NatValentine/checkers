package com.natvalentine.player.queries.responses;

public record PlayerResponse(String id, String userId, String gameId, String color, Boolean isCurrent) {
}
