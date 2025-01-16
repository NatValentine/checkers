package com.natvalentine.board.commands;

import com.natvalentine.generics.utils.Command;

public class CreateBoardCommand extends Command {
    public CreateBoardCommand(String aggregateId) {
        super(aggregateId);
    }
}
