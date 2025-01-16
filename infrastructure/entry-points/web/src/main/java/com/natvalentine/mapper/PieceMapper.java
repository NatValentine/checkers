package com.natvalentine.mapper;

import com.natvalentine.board.queries.responses.PieceResponse;
import com.natvalentine.data.PieceResponseDTO;

public class PieceMapper {
    public static PieceResponseDTO fromEntity(PieceResponse pieceResponse) {
        return new PieceResponseDTO(
                pieceResponse.id(),
                pieceResponse.type(),
                pieceResponse.isActive(),
                pieceResponse.color(),
                pieceResponse.startingLocationX(),
                pieceResponse.startingLocationY()
        );
    }
}
