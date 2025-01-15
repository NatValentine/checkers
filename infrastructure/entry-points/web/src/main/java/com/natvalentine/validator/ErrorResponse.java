package com.natvalentine.validator;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(description = "Default error response format")
public class ErrorResponse {

    @Schema(description = "Http Status", example = "BAD_REQUEST")
    private HttpStatus status;

    @Schema(description = "Error details", example = "")
    private String error;

    private LocalDateTime timestamp;

    public ErrorResponse(HttpStatus status, String error) {
        this.status = status;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}