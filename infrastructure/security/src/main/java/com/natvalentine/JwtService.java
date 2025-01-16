package com.natvalentine;

public interface JwtService {
    public String generateToken(String username, String role);
}
