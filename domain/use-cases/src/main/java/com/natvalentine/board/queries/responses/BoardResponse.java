package com.natvalentine.board.queries.responses;

import com.natvalentine.gateway.dto.PieceDTO;
import com.natvalentine.gateway.dto.TileDTO;

import java.util.ArrayList;

public record BoardResponse(String id, ArrayList<TileDTO> tiles, ArrayList<PieceDTO> pieces, String gameId) {
}
