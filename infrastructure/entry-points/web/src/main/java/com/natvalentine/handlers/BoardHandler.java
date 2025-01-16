package com.natvalentine.handlers;

import com.natvalentine.board.commands.usecases.CreateBoardUseCase;
import com.natvalentine.data.BoardRequestDTO;
import com.natvalentine.mapper.BoardMapper;
import com.natvalentine.validator.RequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BoardHandler {
    private final RequestValidator requestValidator;
    private final CreateBoardUseCase createBoardUseCase;

    public BoardHandler(RequestValidator requestValidator, CreateBoardUseCase createBoardUseCase) {
        this.requestValidator = requestValidator;
        this.createBoardUseCase = createBoardUseCase;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(BoardRequestDTO.class)
                .doOnNext(requestValidator::validate)
                .map(BoardMapper::toEntity)
                .flatMap(createBoardUseCase::execute)
                .map(BoardMapper::fromEntity)
                .flatMap(boardResponseDTO -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(boardResponseDTO));
    }
}
