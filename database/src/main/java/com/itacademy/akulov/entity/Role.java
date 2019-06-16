package com.itacademy.akulov.entity;

public enum Role {

    ALL(""),
    ADMIN("ADMIN"),
    TEACH("TEACH"),
    STUDY("STUDY");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
