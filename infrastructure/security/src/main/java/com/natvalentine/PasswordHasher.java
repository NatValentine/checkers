package com.natvalentine;

public interface PasswordHasher {
    String hashPassword(String password);
    boolean verifyPassword(String rawPassword, String hashedPassword);
}
