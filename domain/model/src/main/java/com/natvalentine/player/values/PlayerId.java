package com.natvalentine.player.values;

import com.natvalentine.generics.utils.Identity;

public class PlayerId extends Identity {
    public PlayerId() {
        super();
    }

    private PlayerId(String value) {
        super(value);
    }

    public static PlayerId of(final String id) {
        return new PlayerId(id);
    }
}
