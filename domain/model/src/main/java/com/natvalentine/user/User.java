package com.natvalentine.user;

import com.natvalentine.generics.utils.Entity;
import com.natvalentine.user.values.UserId;
import com.natvalentine.user.values.objects.Username;

public class User extends Entity<UserId> {
    private final Username username;

    protected User(UserId id, Username username) {
        super(id);
        this.username = username;
    }

    public Username getUsername() {
        return username;
    }
}
