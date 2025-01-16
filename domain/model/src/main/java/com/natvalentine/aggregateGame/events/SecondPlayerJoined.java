package com.natvalentine.aggregateGame.events;

import com.natvalentine.generics.domain.DomainEvent;
import com.natvalentine.player.Player;
import com.natvalentine.player.values.objects.IsCurrent;

import java.util.ArrayList;

public class SecondPlayerJoined extends DomainEvent {
    private final ArrayList<Player> players;

    public SecondPlayerJoined(ArrayList<Player> players) {
        super(GameEventsEnum.SECOND_PLAYER_JOINED.name());
        this.players = players;
        setStartingPlayer();
    }

    private void setStartingPlayer() {
        int index = (int)(Math.random() * this.players.size());
        if (index == 0) {
            this.players.get(0).setIsCurrent(IsCurrent.of(true));
            this.players.get(1).setIsCurrent(IsCurrent.of(false));
        } else {
            this.players.get(1).setIsCurrent(IsCurrent.of(true));
            this.players.get(0).setIsCurrent(IsCurrent.of(false));
        }
    }
}
