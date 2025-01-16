package com.natvalentine.board.queries.usecases;

import com.natvalentine.gateway.IBoardRepository;
import com.natvalentine.gateway.dto.BoardDTO;
import com.natvalentine.generics.interfaces.IUseCaseAccept;

public class BoardSavedViewUseCase implements IUseCaseAccept<BoardDTO, Void> {
    private final IBoardRepository boardRepository;

    public BoardSavedViewUseCase(IBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void accept(BoardDTO boardDTO) {
        boardRepository.save(boardDTO).subscribe();
    }
}
