package com.course.calypso.java9.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class MethodReferenceDemo {

    public void accessPrintlnUsingMethodReference() {

        //Using a lambda expression
        log.info("Print using lambda");
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(x -> System.out.println(x));


        // Using a method reference
        log.info("Print using Method Reference");
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(System.out::println);

        //Assigning the method reference to a functional interface
        log.info("Print using Method Reference to a functional interface");
        Consumer<Integer> printer = System.out::println;
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(printer);
    }

    public void printRandomIntegersLimitToTen() {
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    public void methodReferenceAndEquivalentLambda() {

        //  Invoking a multiple-argument instance method from a class reference
        List<String> strings =
                Arrays.asList("this", "is", "a", "list", "of", "strings");
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());

        List<String> sortedByRef = strings.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        sortedByRef.stream().forEach(System.out::println);
        sorted.stream().forEach(System.out::println);

        log.info("------------- String Length----------------");
        Stream.of("this", "is", "a", "stream", "of", "strings")
                .map(String::length)
                .forEach(System.out::println);

    }
}
