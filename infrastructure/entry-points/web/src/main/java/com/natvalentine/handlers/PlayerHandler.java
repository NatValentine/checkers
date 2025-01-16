package com.natvalentine.handlers;

import com.natvalentine.data.PlayerRequestDTO;
import com.natvalentine.mapper.PlayerMapper;
import com.natvalentine.player.commands.usecases.CreatePlayerUseCase;
import com.natvalentine.validator.RequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PlayerHandler {
    private final RequestValidator requestValidator;
    private final CreatePlayerUseCase createPlayerUseCase;

    public PlayerHandler(RequestValidator requestValidator, CreatePlayerUseCase createPlayerUseCase) {
        this.requestValidator = requestValidator;
        this.createPlayerUseCase = createPlayerUseCase;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(PlayerRequestDTO.class)
                .doOnNext(requestValidator::validate)
                .map(PlayerMapper::toEntity)
                .flatMap(createPlayerUseCase::execute)
                .map(PlayerMapper::fromEntity)
                .flatMap(playerResponseDTO -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(playerResponseDTO));
    }
}
