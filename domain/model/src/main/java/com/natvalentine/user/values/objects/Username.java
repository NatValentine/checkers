package com.natvalentine.user.values.objects;

import com.natvalentine.generics.interfaces.IValueObject;

public class Username implements IValueObject<String> {
    private final String value;

    private Username (final String value) {
        this.value = validate(value);
    }

    public static Username of(final String value) {
        return new Username(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        return value;
    }
}
