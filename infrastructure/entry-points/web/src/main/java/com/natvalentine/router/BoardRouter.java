package com.natvalentine.router;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

public class BoardRouter {

    public RouterFunction<ServerResponse> boardRoutes() {
        return RouterFunctions
                .route(RequestPredicates.POST("/boards")
                        .and(accept(MediaType.APPLICATION_JSON)), playerHandler::create);
    }
}
