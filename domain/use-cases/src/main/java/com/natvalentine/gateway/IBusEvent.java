package com.natvalentine.gateway;

import com.natvalentine.generics.domain.DomainEvent;
import reactor.core.publisher.Mono;

public interface IBusEvent {
    void sendEventUserCreated(Mono<DomainEvent> event);
}
