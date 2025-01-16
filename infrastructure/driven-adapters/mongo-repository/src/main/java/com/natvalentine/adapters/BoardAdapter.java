package com.natvalentine.adapters;

import com.natvalentine.data.BoardEntity;
import com.natvalentine.database.checkers.IBoardMongoRepository;
import com.natvalentine.gateway.IBoardRepository;
import com.natvalentine.gateway.dto.BoardDTO;
import com.natvalentine.mapper.BoardEntityMapper;
import com.natvalentine.mapper.TileEntityMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
public class BoardAdapter implements IBoardRepository {
    private final IBoardMongoRepository boardMongoRepository;
    private final ReactiveMongoTemplate checkersMongoTemplate;

    public BoardAdapter(IBoardMongoRepository boardMongoRepository, @Qualifier("checkersMongoTemplate") ReactiveMongoTemplate checkersMongoTemplate) {
        this.boardMongoRepository = boardMongoRepository;
        this.checkersMongoTemplate = checkersMongoTemplate;
    }


    @Override
    public Mono<BoardDTO> save(BoardDTO board) {
        BoardEntity boardEntity = new BoardEntity(
                board.getId(),
                board.getTiles().stream().map(TileEntityMapper::toEntity).collect(Collectors.toCollection(ArrayList::new)),
                board.getGameId()
        );
        return boardMongoRepository.save(boardEntity).map(BoardEntityMapper::toDTO);
    }

    @Override
    public Mono<BoardDTO> getOne(String id) {
        return null;
    }

    @Override
    public Mono<BoardDTO> update(BoardDTO board) {
        return null;
    }
}
