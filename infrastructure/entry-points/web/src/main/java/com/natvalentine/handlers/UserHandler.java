package com.natvalentine.handlers;

import com.natvalentine.data.UserRequestDTO;
import com.natvalentine.mapper.UserMapper;
import com.natvalentine.user.commands.usecases.CreateUserUseCase;
import com.natvalentine.user.queries.usecases.GetAllUsersViewUseCase;
import com.natvalentine.user.queries.usecases.GetUserByUsernameViewUseCase;
import com.natvalentine.validator.RequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    private final RequestValidator requestValidator;
    private final CreateUserUseCase createUserUseCase;
    private final GetAllUsersViewUseCase getAllUsersViewUseCase;
    private final GetUserByUsernameViewUseCase getUserByUsernameViewUseCase;

    public UserHandler(RequestValidator requestValidator, CreateUserUseCase createUserUseCase, GetAllUsersViewUseCase getAllUsersViewUseCase, GetUserByUsernameViewUseCase getUserByUsernameViewUseCase) {
        this.requestValidator = requestValidator;
        this.createUserUseCase = createUserUseCase;
        this.getAllUsersViewUseCase = getAllUsersViewUseCase;
        this.getUserByUsernameViewUseCase = getUserByUsernameViewUseCase;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(UserRequestDTO.class)
                .doOnNext(requestValidator::validate)
                .map(UserMapper::toEntity)
                .flatMap(createUserUseCase::execute)
                .map(UserMapper::fromEntity)
                .flatMap(userResponseDTO -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(userResponseDTO));
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return getAllUsersViewUseCase.get()
                .map(queryResponse -> queryResponse.getMultipleResults()
                        .stream()
                        .map(UserMapper::fromEntity)
                        .toList())
                .flatMap(userResponseDTOs ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(userResponseDTOs));
    }
}
