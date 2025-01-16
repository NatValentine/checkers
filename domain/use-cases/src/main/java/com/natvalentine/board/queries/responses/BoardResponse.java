package com.natvalentine.board.queries.responses;

import com.natvalentine.board.Tile;

import java.util.ArrayList;

public record BoardResponse(String id, ArrayList<Tile> tiles, String gameId) {
}
