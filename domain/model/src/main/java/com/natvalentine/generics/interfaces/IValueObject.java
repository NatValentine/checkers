package com.natvalentine.generics.interfaces;

//1. Generics creation to apply DDD: ValueObject
public interface IValueObject<T> {
    T getValue();
}
