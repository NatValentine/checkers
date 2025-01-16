package com.natvalentine.board.queries.responses;

import java.util.ArrayList;

public record BoardResponse(String id, ArrayList<TileResponse> tiles, String gameId) {
}
