package com.dmdev.http.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Sex {

    MALE,
    FEMALE;

    public static Optional<Sex> find(String sex) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(sex))
                .findFirst();
    }
}
