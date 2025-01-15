package com.natvalentine.aggregate.events;

import com.natvalentine.generics.domain.DomainEvent;

public class TurnPassed extends DomainEvent {
    public TurnPassed() {
        super(EventsEnum.TURN_PASSED.name());
        // check game state, trigger game over or next turn
    }
}
