package com.natvalentine.mapper;

import com.natvalentine.data.UserEntity;
import com.natvalentine.gateway.dto.UserDTO;

public class UserEntityMapper {
    public static UserEntity toEntity(UserDTO userDTO){
        return new UserEntity(userDTO.getId(), userDTO.getUsername());
    }

    public static UserDTO fromEntity(UserEntity user){
        return new UserDTO(user.getId(), user.getUsername());
    }
}
