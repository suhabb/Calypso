package com.course.calypso.dto;

import lombok.ToString;

@ToString
public class Order {
    private int id;

    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}