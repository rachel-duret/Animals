package com.Alphanetworks.Animals.models;

public enum AnimalType {
    DOG("Dog"),
    CAT("Cat"),
    FISH("Fish");

    private final String name;

    AnimalType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
