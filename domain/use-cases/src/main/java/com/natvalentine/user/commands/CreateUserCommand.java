package com.natvalentine.user.commands;

import com.natvalentine.generics.utils.Command;

public class CreateUserCommand extends Command {
    private final String username;
    private final String password;

    public CreateUserCommand(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
