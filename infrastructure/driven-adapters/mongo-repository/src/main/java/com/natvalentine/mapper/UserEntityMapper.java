package com.natvalentine.mapper;

import com.natvalentine.data.UserEntity;
import com.natvalentine.gateway.dto.UserDTO;
import com.natvalentine.utils.RolesEnum;

public class UserEntityMapper {
    public static UserEntity toEntity(UserDTO userDTO){
        return new UserEntity(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());
    }

    public static UserDTO fromEntity(UserEntity user){
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
