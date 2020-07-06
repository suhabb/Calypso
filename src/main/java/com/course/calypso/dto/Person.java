package com.course.calypso.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

}