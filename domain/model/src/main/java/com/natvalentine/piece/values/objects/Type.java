package com.natvalentine.piece.values.objects;

import com.natvalentine.generics.interfaces.IValueObject;

public class Type implements IValueObject<String> {
    private final String value;

    private Type(final String value) {
        this.value = validate(value);
    }

    public static Type of(final String value) {
        return new Type(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        return value;
    }
}
