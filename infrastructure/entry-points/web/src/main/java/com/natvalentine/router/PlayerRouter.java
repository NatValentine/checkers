package com.natvalentine.router;

import com.natvalentine.data.PlayerRequestDTO;
import com.natvalentine.data.PlayerResponseDTO;
import com.natvalentine.handlers.PlayerHandler;
import com.natvalentine.validator.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class PlayerRouter {
    private final PlayerHandler playerHandler;

    public PlayerRouter(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/players",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"Players"},
                            operationId = "join-game",
                            summary = "Join a game of checkers",
                            description = "This endpoint registers a new player in the current game.",
                            requestBody = @RequestBody(
                                    description = "UserId of user that wants to create the player",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PlayerRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Player successfully created",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlayerResponseDTO.class))
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
    public RouterFunction<ServerResponse> playerRoutes() {
        return RouterFunctions
                .route(RequestPredicates.POST("/players")
                        .and(accept(MediaType.APPLICATION_JSON)), playerHandler::create);
    }
}
