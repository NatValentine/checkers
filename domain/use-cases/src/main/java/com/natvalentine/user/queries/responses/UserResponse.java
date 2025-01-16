package com.natvalentine.user.queries.responses;

public class UserResponse {
    private final String id;
    private final String username;
    private final String role;
    private String token = "";

    public UserResponse(String id, String username, String role, String token) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
