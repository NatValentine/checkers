package com.natvalentine.mapper;

import com.natvalentine.data.UserRequestDTO;
import com.natvalentine.data.UserResponseDTO;
import com.natvalentine.user.commands.CreateUserCommand;
import com.natvalentine.user.queries.responses.UserResponse;

public class UserMapper {
    public static UserResponseDTO fromEntity(UserResponse userResponse) {
        return new UserResponseDTO(userResponse.id(), userResponse.username());
    }

    public static CreateUserCommand toEntity(UserRequestDTO userRequestDTO) {
        return new CreateUserCommand(userRequestDTO.getUsername());
    }
}
