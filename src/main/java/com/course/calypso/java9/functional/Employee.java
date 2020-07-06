package com.course.calypso.java9.functional;

public interface Employee {

    String getFirst();

    String getLast();

    void convertCaffeineToCodeForMoney();

    //interface can have a default method
    default String getName() {
        return String.format("%s %s", getFirst(), getLast());
    }
}