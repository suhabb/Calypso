package com.course.calypso.java9.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {

    public void consumerFunction(){

        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
            } });
        strings.forEach(s -> System.out.println(s));
        strings.forEach(System.out::println);
    }
}
