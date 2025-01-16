package com.natvalentine.generics.utils;

import com.natvalentine.generics.domain.DomainActionsContainer;
import com.natvalentine.generics.domain.DomainActionsHandler;
import com.natvalentine.generics.domain.DomainEvent;
import com.natvalentine.generics.interfaces.IApplyEvent;
import reactor.core.publisher.Flux;

import java.util.List;

public abstract class AggregateRoot<I extends Identity> extends Entity<I> {
    private final DomainActionsHandler actionsHandler= new DomainActionsHandler();

    protected AggregateRoot(final I id) {
        super(id);
    }

    public Flux<DomainEvent> getUncommittedEvents() {
        return Flux.fromIterable(actionsHandler.getEvents());
    }

    public void markEventsAsCommitted() {
        actionsHandler.getEvents().clear();
    }

    protected void setSubscription(final DomainActionsContainer container) {
        actionsHandler.subscribe(container);
    }

    protected IApplyEvent addEvent(final DomainEvent event) {
        final String aggregateName = this.getId()
                .getClass()
                .getSimpleName()
                .replace("Id", "")
                .toLowerCase();
        event.setAggregateRootId(getId().getValue());
        event.setAggregateRootName(aggregateName);

        return actionsHandler.append(event);
    }
}