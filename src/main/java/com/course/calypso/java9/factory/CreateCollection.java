package com.course.calypso.java9.factory;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
* Creating Immutable Collections Problem
You want to create immutable lists, sets, or maps in Java 9.
Solution
Use the static factory methods List.of, Set.of, and Map.of available in Java 9.
* */
public class CreateCollection {

    public void showImmutabilityAdd() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.add(99);
    }


    public Map<String, String> immutableMapFromEntries() throws Exception {

        Map<String, String> jvmLanguages = Map.ofEntries(
                Map.entry("Java", "http://www.oracle.com/technetwork/java/index.html"),
                Map.entry("Groovy", "http://groovy-lang.org/"),
                Map.entry("Scala", "http://www.scala-lang.org/"),
                Map.entry("Clojure", "https://clojure.org/"),
                Map.entry("Kotlin", "http://kotlinlang.org/"));

        //set
        Set<String> names = Set.of("Java", "Scala", "Groovy", "Clojure", "Kotlin");

        //list
        List<String> urls = List.of("http://www.oracle.com/technetwork/java/index.html",
                "http://groovy-lang.org/",
                "http://www.scala-lang.org/",
                "https://clojure.org/",
                "http://kotlinlang.org/");

        Set<String> keys = jvmLanguages.keySet();
        keys.stream().forEach(System.out::println);

        Collection<String> values = jvmLanguages.values();
        values.stream().forEach(System.out::println);
        Map<String, String> javaMap = Map.of("Java",
                "http://www.oracle.com/technetwork/java/index.html",
                "Groovy",
                "http://groovy-lang.org/",
                "Scala",
                "http://www.scala-lang.org/",
                "Clojure",
                "https://clojure.org/",
                "Kotlin",
                "http://kotlinlang.org/");
        return javaMap;
    }

    /*
    The first stream is the Java 8 way of using iterate with a limit.
    The second one uses a Predicate as the second argument. The result looks more like a traditional for loop.
    */
    public void iterate() throws Exception {
        //Java 8 way to create a stream of big decimals
        List<BigDecimal> bigDecimals =
                Stream.iterate(BigDecimal.ZERO,
                        bd -> bd.add(BigDecimal.ONE))
                        .limit(10)
                        .collect(Collectors.toList());
        System.out.println("Big Decimal :" + bigDecimals.size());


        //Java 9 way to create stream of big decimals
        bigDecimals = Stream.iterate(BigDecimal.ZERO, bd -> bd.longValue() < 10L,//predicate
                bd -> bd.add(BigDecimal.ONE))
                .collect(Collectors.toList());
        System.out.println("Big Decimal :" + bigDecimals.size());
    }

    /*
    takeWhile and dropWhile

    The new methods takeWhile and dropWhile allow you to get portions of a stream based on a predicate.
    According to the Javadocs, on an ordered stream, takeWhile returns “the longest prefix of elements taken from this stream
    that match the given predicate,” starting at the beginning of the stream.

    */

    public void takeWhile() throws Exception {
        List<String> strings = Stream.of("this is a list of strings".split(" "))
                .takeWhile(s -> !s.equals("of"))//Return stream up to where predicate fails
                .collect(Collectors.toList());
        List<String> correct = Arrays.asList("this", "is", "a", "list");
        correct.stream().forEach(System.out::println);
    }

    /*
    The dropWhile method does the opposite—it returns the remaining elements of the stream after dropping the longest prefix
    of elements that satisfy the predicate.
    */
    public void dropWhile() throws Exception {
        List<String> strings = Stream.of("this is a list of strings".split(" "))
                .dropWhile(s -> !s.equals("of"))//Return stream after predicate fails
                .collect(Collectors.toList());
        List<String> correct = Arrays.asList("of", "strings");
        correct.stream().forEach(System.out::println);
    }
}
