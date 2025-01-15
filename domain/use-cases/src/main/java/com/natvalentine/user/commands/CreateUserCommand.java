package com.natvalentine.user.commands;

import com.natvalentine.generics.utils.Command;

public class CreateUserCommand extends Command {
    private final String username;

    public CreateUserCommand(String username) {
        super(null);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
