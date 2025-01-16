package com.natvalentine.user.values.objects;

import com.natvalentine.generics.interfaces.IValueObject;

public class Password implements IValueObject<String> {
    private final String value;

    private Password (final String value) {
        this.value = validate(value);
    }

    public static Password of(final String value) {
        return new Password(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        return value;
    }
}
