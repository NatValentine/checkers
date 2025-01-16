package com.natvalentine.mapper;

import com.natvalentine.data.PlayerEntity;
import com.natvalentine.gateway.dto.PlayerDTO;

public class PlayerEntityMapper {
    public static PlayerEntity toEntity(PlayerDTO playerDTO){
        return new PlayerEntity(
                playerDTO.getId(),
                playerDTO.getUserId(),
                playerDTO.getGameId(),
                playerDTO.getColor(),
                playerDTO.getCurrent()
        );
    }

    public static PlayerDTO fromEntity(PlayerEntity player) {
        return new PlayerDTO(
                player.getId(),
                player.getUserId(),
                player.getGameId(),
                player.getColor(),
                player.getCurrent()
        );
    }
}
