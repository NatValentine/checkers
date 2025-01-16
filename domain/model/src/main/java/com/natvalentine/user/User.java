package com.natvalentine.user;

import com.natvalentine.generics.utils.Entity;
import com.natvalentine.user.values.UserId;
import com.natvalentine.user.values.objects.Password;
import com.natvalentine.user.values.objects.Username;

public class User extends Entity<UserId> {
    private final Username username;
    private final Password password;

    private final String role;

    public User(UserId id, Username username, Password password, String role) {
        super(id);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
