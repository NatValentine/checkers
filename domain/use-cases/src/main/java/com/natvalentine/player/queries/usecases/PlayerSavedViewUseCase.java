package com.natvalentine.player.queries.usecases;

import com.natvalentine.gateway.IPlayerRepository;
import com.natvalentine.gateway.dto.PlayerDTO;
import com.natvalentine.generics.interfaces.IUseCaseAccept;

public class PlayerSavedViewUseCase implements IUseCaseAccept<PlayerDTO, Void> {
    private final IPlayerRepository playerRepository;

    public PlayerSavedViewUseCase(IPlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void accept(PlayerDTO playerDTO) {
        playerRepository.save(playerDTO).subscribe();
    }
}
