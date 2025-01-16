package com.natvalentine.mapper;

import com.natvalentine.aggregateGame.values.GameId;
import com.natvalentine.data.PlayerRequestDTO;
import com.natvalentine.data.PlayerResponseDTO;
import com.natvalentine.player.commands.CreatePlayerCommand;
import com.natvalentine.player.queries.responses.PlayerResponse;

public class PlayerMapper {
    public static PlayerResponseDTO fromEntity(PlayerResponse playerResponse) {
        return new PlayerResponseDTO(playerResponse.id(), playerResponse.userId(), playerResponse.color(), playerResponse.isCurrent(), playerResponse.gameId());
    }

    public static CreatePlayerCommand toEntity(PlayerRequestDTO playerRequestDTO) {
        return new CreatePlayerCommand(playerRequestDTO.gameId(), playerRequestDTO.userId());
    }
}
