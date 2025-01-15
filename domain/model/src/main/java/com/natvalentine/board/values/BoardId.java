package com.natvalentine.board.values;

import com.natvalentine.generics.utils.Identity;

public class BoardId extends Identity {
    public BoardId() {
        super();
    }

    private BoardId(String id) {
        super(id);
    }

    public static BoardId of(final String id) {
        return new BoardId(id);
    }
}
