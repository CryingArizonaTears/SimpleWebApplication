package com.godel.model;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other"); // 2022 :)


    Gender(String name) {
        this.name = name;
    }

    private final String name;
}
