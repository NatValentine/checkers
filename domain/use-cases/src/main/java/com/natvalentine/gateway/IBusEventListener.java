package com.natvalentine.gateway;

import com.natvalentine.generics.domain.DomainEvent;

public interface IBusEventListener {
    void receiveUserCreated(DomainEvent event);
    void receivePlayerCreated(DomainEvent event);
    void receiveBoardCreated(DomainEvent event);
}
