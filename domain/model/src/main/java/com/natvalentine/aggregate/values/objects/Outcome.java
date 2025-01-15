package com.natvalentine.aggregate.values.objects;

import com.natvalentine.generics.interfaces.IValueObject;

public class Outcome implements IValueObject<String> {
    private final String value;

    private Outcome (final String value) {
        this.value = validate(value);
    }

    public static Outcome of(final String value) {
        return new Outcome(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        return value;
    }
}
