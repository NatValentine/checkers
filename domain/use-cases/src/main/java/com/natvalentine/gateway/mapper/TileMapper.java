package com.natvalentine.gateway.mapper;

import com.natvalentine.board.Tile;
import com.natvalentine.gateway.dto.TileDTO;

public class TileMapper {
    public static TileDTO toDTO(Tile tile) {
        return new TileDTO(
                tile.getLocation().getValue().x(),
                tile.getLocation().getValue().y(),
                tile.getPiece() != null ? PieceMapper.toDTO(tile.getPiece()) : null
        );
    }

    public static Tile toEntity(TileDTO dto) {
        Tile tile = new Tile(dto.getX(), dto.getY());
        if (dto.getPiece() != null) {
            tile.setPiece(PieceMapper.toEntity(dto.getPiece()));
        }
        return tile;
    }
}
