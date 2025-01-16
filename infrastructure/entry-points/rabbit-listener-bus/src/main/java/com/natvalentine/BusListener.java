package com.natvalentine;


import com.natvalentine.user.events.UserCreated;
import com.natvalentine.gateway.IBusEventListener;
import com.natvalentine.gateway.dto.UserDTO;
import com.natvalentine.generics.domain.DomainEvent;
import com.natvalentine.user.queries.usecases.UserSavedViewUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BusListener implements IBusEventListener {

    private final UserSavedViewUseCase userSavedViewUseCase;

    public BusListener(UserSavedViewUseCase userSavedViewUseCase) {
        this.userSavedViewUseCase = userSavedViewUseCase;
    }

    @Override
    @RabbitListener(queues = "#{@rabbitProperties.getUserQueue()}")
    public void receiveUserCreated(DomainEvent event) {
        UserCreated userCreated = (UserCreated) event;
        UserDTO userDTO = new UserDTO(
                userCreated.getId(),
                userCreated.getUsername(),
                userCreated.getPassword(),
                userCreated.getRole()
        );
        userSavedViewUseCase.accept(userDTO);
    }
}
