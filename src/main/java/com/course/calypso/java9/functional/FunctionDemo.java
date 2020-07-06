package com.course.calypso.java9.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;
import java.util.function.Function;


@Slf4j
public class FunctionDemo {

    public Double functionToMultiplyBy10(Integer number) {

        Function<Integer, Double> ourFunc = new Function<Integer, Double>() {
            @Override
            public Double apply(Integer number) {
                return number * 10.0;
            }
        };
        return ourFunc.apply(number);
    }

    public Integer functionToParse(String integer) {

        Function<String, Integer> ourFunc = number -> Integer.parseInt(number);
        return ourFunc.apply(integer);
    }

    /*
     BiFunction<T,U,R> : Accepted two parameters T,U and returns the value R.
     */
    public Double biFunctional(String t, Integer u) {
        //BiFunction<T,U,R>
        BiFunction<String, Integer, Double> biFunction = new BiFunction<String, Integer, Double>() {
            @Override
            public Double apply(String s, Integer i) {
                return (s.length() * 10d) / i;
            }
        };
        return biFunction.apply(t, u);
    }


}
