package com.natvalentine.gateway;

import com.natvalentine.generics.domain.DomainEvent;

public interface IBusEvent {
    void sendEventUserCreated(DomainEvent event);
    void sendEventBoardCreated(DomainEvent event);
    void sendEventPlayerCreated(DomainEvent event);
}
