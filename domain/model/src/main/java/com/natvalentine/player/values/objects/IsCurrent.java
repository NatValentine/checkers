package com.natvalentine.player.values.objects;

import com.natvalentine.generics.interfaces.IValueObject;

public class IsCurrent implements IValueObject<Boolean> {
    private final Boolean value;

    private IsCurrent (final Boolean value) {
        this.value = validate(value);
    }

    public static IsCurrent of(final Boolean value) {
        return new IsCurrent(value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    private Boolean validate(final Boolean value){
        if(value == null){
            throw new IllegalArgumentException("IsCurrent can't be null");
        }

        return value;
    }
}
