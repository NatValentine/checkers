package com.natvalentine.aggregateGame.values.objects;

import com.natvalentine.generics.interfaces.IValueObject;

public class Turn implements IValueObject<Integer> {
    private final Integer value;

    private Turn (final Integer value) {
        this.value = validate(value);
    }

    public static Turn of(final Integer value) {
        return new Turn(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    private Integer validate(final Integer value){
        if(value == null){
            throw new IllegalArgumentException("The turn can't be null");
        }

        if(value < 0){
            throw new IllegalArgumentException("The current turn can't be less than 0");
        }

        return value;
    }
}
