package com.natvalentine.aggregateGame.events;

import com.natvalentine.generics.domain.DomainEvent;

public class TurnPassed extends DomainEvent {
    public TurnPassed() {
        super(GameEventsEnum.TURN_PASSED.name());
        // check game state, trigger game over or next turn
    }
}
