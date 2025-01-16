package com.natvalentine.router;

import com.natvalentine.data.BoardRequestDTO;
import com.natvalentine.data.BoardResponseDTO;
import com.natvalentine.data.PlayerRequestDTO;
import com.natvalentine.data.PlayerResponseDTO;
import com.natvalentine.handlers.BoardHandler;
import com.natvalentine.validator.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

public class BoardRouter {
    private final BoardHandler boardHandler;

    public BoardRouter(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
    }

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/boards",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"Boards"},
                            operationId = "create",
                            summary = "Create a new board for a game",
                            description = "This endpoint registers a board in the current game.",
                            requestBody = @RequestBody(
                                    description = "GameId of target Game",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BoardRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Board successfully created",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BoardResponseDTO.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Bad request, validation error or missing required fields",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> boardRoutes() {
        return RouterFunctions
                .route(RequestPredicates.POST("/boards")
                        .and(accept(MediaType.APPLICATION_JSON)), boardHandler::create);
    }
}
