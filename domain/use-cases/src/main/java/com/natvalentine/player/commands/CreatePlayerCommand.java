package com.natvalentine.player.commands;

import com.natvalentine.generics.utils.Command;

public class CreatePlayerCommand extends Command {
    private final String userId;

    protected CreatePlayerCommand(String aggregateId, String userId) {
        super(aggregateId);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
