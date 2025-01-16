package com.natvalentine.gateway.mapper;

import com.natvalentine.board.Tile;
import com.natvalentine.gateway.dto.PieceDTO;
import com.natvalentine.piece.Piece;
import com.natvalentine.piece.values.PieceId;
import com.natvalentine.piece.values.objects.Color;
import com.natvalentine.piece.values.objects.IsActive;
import com.natvalentine.piece.values.objects.Type;

public class PieceMapper {
    public static PieceDTO toDTO(Piece piece) {
        return new PieceDTO(
                piece.getId().getValue(),
                piece.getType().getValue(),
                piece.getIsActive().getValue(),
                piece.getColor().getValue(),
                TileMapper.toDTO(piece.getStartingLocation())
        );
    }

    public static Piece toEntity(PieceDTO dto) {
        Tile startingLocation = TileMapper.toEntity(dto.getStartingLocation());
        return new Piece(new PieceId(dto.getId()), startingLocation, IsActive.of(dto.getActive()), Color.of(dto.getColor()), Type.of(dto.getType()));
    }
}
