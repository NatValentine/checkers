package com.natvalentine.mapper;

import com.natvalentine.data.TileEntity;
import com.natvalentine.gateway.dto.TileDTO;

public class TileEntityMapper {
    public static TileEntity toEntity(TileDTO tileDTO){
        return new TileEntity(tileDTO.getX(), tileDTO.getY(), PieceEntityMapper.toEntity(tileDTO.getPiece()));
    }

    public static TileDTO fromEntity(TileEntity tile){
        return new TileDTO(tile.getX(), tile.getY(), PieceEntityMapper.fromEntity(tile.getPiece()));
    }
}
