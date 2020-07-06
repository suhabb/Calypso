package com.course.calypso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String name;

    private Integer id;

    private double price;


    public Book(String name) {
        this.name = name;
    }

}