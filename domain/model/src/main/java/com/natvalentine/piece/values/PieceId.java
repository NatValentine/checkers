package com.natvalentine.piece.values;

import com.natvalentine.generics.utils.Identity;

public class PieceId extends Identity {
    public PieceId() {
        super();
    }

    private PieceId(String value) {
        super(value);
    }

    public static PieceId of(final String id) {
        return new PieceId(id);
    }
}
