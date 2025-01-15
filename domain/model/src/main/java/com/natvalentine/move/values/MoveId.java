package com.natvalentine.move.values;

import com.natvalentine.generics.utils.Identity;

public class MoveId extends Identity {
    public MoveId() {
        super();
    }

    private MoveId(String value) {
        super(value);
    }

    public static MoveId of(final String id) {
        return new MoveId(id);
    }
}
