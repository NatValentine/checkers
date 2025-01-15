package com.natvalentine.aggregate.values;

import com.natvalentine.generics.utils.Identity;

public class GameId extends Identity {
    public GameId() {
        super();
    }

    private GameId(final String id) {
        super(id);
    }

    public static GameId of(final String id) {
        return new GameId(id);
    }
}
