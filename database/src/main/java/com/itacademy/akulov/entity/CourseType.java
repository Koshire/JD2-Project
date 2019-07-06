package com.itacademy.akulov.entity;

public enum CourseType {

    All(""),
    EXTRAMURAL("EXTRAMURAL"),
    DISTANCE("DISTANCE"),
    FULL_TIME("FULL_TIME");

    private String name;

    CourseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
