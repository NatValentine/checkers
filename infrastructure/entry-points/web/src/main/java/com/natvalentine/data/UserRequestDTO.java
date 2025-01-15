package com.natvalentine.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50)
    private String username;

    public UserRequestDTO() {}

    public UserRequestDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
