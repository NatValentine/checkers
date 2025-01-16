package com.natvalentine.mapper;

import com.natvalentine.board.commands.CreateBoardCommand;
import com.natvalentine.board.queries.responses.BoardResponse;
import com.natvalentine.data.BoardRequestDTO;
import com.natvalentine.data.BoardResponseDTO;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BoardMapper {
    public static BoardResponseDTO fromEntity(BoardResponse boardResponse) {
        return new BoardResponseDTO(boardResponse.id(), boardResponse.gameId(), boardResponse.tiles().stream().map(TileMapper::fromEntity).collect(Collectors.toCollection(ArrayList::new)));
    }

    public static CreateBoardCommand toEntity(BoardRequestDTO boardRequestDTO) {
        return new CreateBoardCommand(boardRequestDTO.gameId());
    }
}
