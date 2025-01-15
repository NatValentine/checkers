package com.natvalentine.user.queries.usecases;

import com.natvalentine.gateway.IUserRepository;
import com.natvalentine.gateway.dto.UserDTO;
import com.natvalentine.generics.interfaces.IUseCaseAccept;

public class UserSavedViewUseCase implements IUseCaseAccept<UserDTO, Void> {
    private final IUserRepository userRepository;

    public UserSavedViewUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void accept(UserDTO userDTO){
        userRepository.save(userDTO).subscribe();
    }
}
