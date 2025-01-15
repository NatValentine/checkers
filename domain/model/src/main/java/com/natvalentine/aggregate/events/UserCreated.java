package com.natvalentine.aggregate.events;

import com.natvalentine.generics.domain.DomainEvent;

public class UserCreated extends DomainEvent {
    private final String id;
    private final String username;

    public UserCreated(String id, String username) {
        super(EventsEnum.USER_CREATED.name());
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
