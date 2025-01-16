package com.natvalentine.mapper;

import com.natvalentine.data.PieceEntity;
import com.natvalentine.data.UserEntity;
import com.natvalentine.gateway.dto.PieceDTO;
import com.natvalentine.gateway.dto.UserDTO;

public class PieceEntityMapper {
    public static PieceEntity toEntity(PieceDTO pieceDTO){
        return new PieceEntity(pieceDTO.getId(), pieceDTO.getType(), pieceDTO.getActive(), pieceDTO.getColor(), TileEntityMapper.toEntity(pieceDTO.getStartingLocation()));
    }

    public static PieceDTO fromEntity(PieceEntity piece){
        return new PieceDTO(piece.getId(), piece.getType(), piece.getActive(), piece.getColor(), TileEntityMapper.fromEntity(piece.getStartingLocation()));
    }
}
