package com.natvalentine;


import com.natvalentine.aggregateGame.events.BoardCreated;
import com.natvalentine.aggregateGame.events.PlayerCreated;
import com.natvalentine.board.queries.usecases.BoardSavedViewUseCase;
import com.natvalentine.gateway.dto.BoardDTO;
import com.natvalentine.gateway.dto.PlayerDTO;
import com.natvalentine.gateway.mapper.TileMapper;
import com.natvalentine.player.queries.usecases.PlayerSavedViewUseCase;
import com.natvalentine.user.events.UserCreated;
import com.natvalentine.gateway.IBusEventListener;
import com.natvalentine.gateway.dto.UserDTO;
import com.natvalentine.generics.domain.DomainEvent;
import com.natvalentine.user.queries.usecases.UserSavedViewUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class BusListener implements IBusEventListener {
    private final UserSavedViewUseCase userSavedViewUseCase;
    private final PlayerSavedViewUseCase playerSavedViewUseCase;
    private final BoardSavedViewUseCase boardSavedViewUseCase;

    public BusListener(UserSavedViewUseCase userSavedViewUseCase, PlayerSavedViewUseCase playerSavedViewUseCase, BoardSavedViewUseCase boardSavedViewUseCase) {
        this.userSavedViewUseCase = userSavedViewUseCase;
        this.playerSavedViewUseCase = playerSavedViewUseCase;
        this.boardSavedViewUseCase = boardSavedViewUseCase;
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

    @Override
    @RabbitListener(queues = "#{@rabbitProperties.getPlayerCreatedQueue()}")
    public void receivePlayerCreated(DomainEvent event) {
        PlayerCreated playerCreated = (PlayerCreated) event;
        PlayerDTO playerDTO = new PlayerDTO(
                playerCreated.getPlayerId(),
                playerCreated.getUserId(),
                playerCreated.getAggregateRootId(),
                playerCreated.getColor(),
                false
        );
        playerSavedViewUseCase.accept(playerDTO);
    }

    @Override
    public void receiveBoardCreated(DomainEvent event) {
        BoardCreated boardCreated = (BoardCreated) event;
        BoardDTO boardDTO = new BoardDTO(
                boardCreated.getId(),
                boardCreated.getTiles().stream().map(TileMapper::toDTO).collect(Collectors.toCollection(ArrayList::new)),
                boardCreated.getAggregateRootId()
        );
        boardSavedViewUseCase.accept(boardDTO);
    }


}
