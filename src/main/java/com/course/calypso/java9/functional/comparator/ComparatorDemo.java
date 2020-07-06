package com.course.calypso.java9.functional.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparatorDemo {

    public static void main(String[] args) {
        new ComparatorDemo().comparatorFunctions();
    }

    public void comparatorFunctions() {


        List<String> bonds = Arrays.asList("Connery", "Lazenby", "Moore",
                "Dalton", "Brosnan", "Craig");

        List<String> sorted = bonds.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        sorted.stream().forEach(System.out::println);
        // [Brosnan, Connery, Craig, Dalton, Lazenby, Moore]

        sorted = bonds.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        // [Moore, Lazenby, Dalton, Craig, Connery, Brosnan]

        sorted = bonds.stream()
                .sorted(Comparator.comparing(String::toLowerCase))
                .collect(Collectors.toList());
        // [Brosnan, Connery, Craig, Dalton, Lazenby, Moore]

        sorted = bonds.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        // [Moore, Craig, Dalton, Connery, Lazenby, Brosnan]

    }
}