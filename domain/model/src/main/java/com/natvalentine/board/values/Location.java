package com.natvalentine.board.values;

import com.natvalentine.generics.interfaces.IValueObject;
import com.natvalentine.generics.utils.Vector2;

public class Location implements IValueObject<Vector2> {
    private final Integer x;
    private final Integer y;

    private Location (final Integer x, final Integer y) {
        this.x = validate(x);
        this.y = validate(y);
    }

    public static Location of(final Integer x, final Integer y) {
        return new Location(x, y);
    }

    @Override
    public Vector2 getValue() {
        return new Vector2(this.x, this.y);
    }

    private Integer validate(final Integer value){
        if(value == null){
            throw new IllegalArgumentException("Coordinate can't be null");
        }

        if(value < 0){
            throw new IllegalArgumentException("Coordinate can't be less than 0");
        }

        return value;
    }
}
