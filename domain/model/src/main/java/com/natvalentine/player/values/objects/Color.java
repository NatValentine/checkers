package com.natvalentine.player.values.objects;

import com.natvalentine.generics.interfaces.IValueObject;

public class Color implements IValueObject<String> {
    private final String value;

    private Color (final String value) {
        this.value = validate(value);
    }

    public static Color of(final String value) {
        return new Color(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        return value;
    }
}
