package com.natvalentine.user.events;

import com.natvalentine.generics.domain.DomainEvent;

public class UserCreated extends DomainEvent {
    private final String id;
    private final String username;
    private final String password;
    private final String role;

    public UserCreated(String id, String username, String password, String role) {
        super(UserEventsEnum.USER_CREATED.name());
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
