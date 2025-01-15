package com.natvalentine.user.values;

import com.natvalentine.generics.utils.Identity;

public class UserId extends Identity {
    public UserId() {
        super();
    }

    private UserId(String value) {
        super(value);
    }

    public static UserId of(final String id) {
        return new UserId(id);
    }
}
