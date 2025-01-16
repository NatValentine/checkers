package com.natvalentine.mapper;

import com.natvalentine.data.BoardEntity;
import com.natvalentine.gateway.dto.BoardDTO;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BoardEntityMapper {
    public static BoardDTO toDTO(BoardEntity entity) {
        return new BoardDTO(
                entity.getId(),
                entity.getTiles().stream().map(TileEntityMapper::fromEntity).collect(Collectors.toCollection(ArrayList::new)),
                entity.getGameId()
        );
    }

    public static BoardEntity toEntity(BoardDTO dto) {
        if (dto == null) {
            return null;
        }

        BoardEntity entity = new BoardEntity();
        entity.setId(dto.getId());
        entity.setTiles(dto.getTiles() != null
                ? dto.getTiles().stream().map(TileEntityMapper::toEntity).collect(Collectors.toCollection(ArrayList::new))
                : new ArrayList<>());
        entity.setGameId(dto.getGameId());
        return entity;
    }
}
