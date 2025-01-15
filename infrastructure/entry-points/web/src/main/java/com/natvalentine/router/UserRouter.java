package com.natvalentine.router;

import com.natvalentine.handlers.UserHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class UserRouter {
    private final UserHandler userHandler;

    public UserRouter(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    public RouterFunction<ServerResponse> userRoutes() {
        return RouterFunctions
                .route(RequestPredicates.POST("/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::create)
                .andRoute(RequestPredicates.GET("/users"), userHandler::getAll);
    }
}
