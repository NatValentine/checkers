package com.natvalentine.mapper;

import com.natvalentine.board.queries.responses.TileResponse;
import com.natvalentine.data.TileResponseDTO;

public class TileMapper {
    public static TileResponseDTO fromEntity(TileResponse tileResponse) {
        return new TileResponseDTO(tileResponse.x(), tileResponse.y(), PieceMapper.fromEntity(tileResponse.piece()));
    }
}
