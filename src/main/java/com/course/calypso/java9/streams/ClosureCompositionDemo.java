package com.course.calypso.java9.streams;


import java.util.function.Function;

/*
* You want to apply a series of small, independent functions consecutively.

Use the composition methods defined as defaults in the Function, Consumer, and Predicate interfaces.
*
* */
public class ClosureCompositionDemo {

    /*The compose method applies its argument before the original function, while the andThen method applies its argument
    after the original function.*/
    public int composeFunction(int arg) {

        Function<Integer, Integer> add2 = x -> x + 2;
        Function<Integer, Integer> mult3 = x -> x * 3;
        Function<Integer, Integer> mult3add2 = add2.compose(mult3);//because 2 + (2 * 3)  = 8
        int sum = mult3add2.apply(arg);//INVOKE THE FUNCTION
        System.out.println("Compose: " + sum);
        return sum;

    }

    public int andThenFunction(int arg) {
        Function<Integer, Integer> add2 = x -> x + 2;
        Function<Integer, Integer> mult3 = x -> x * 3;//
        Function<Integer, Integer> add2mult3 = add2.andThen(mult3); // because (2 + 2) * 3 == 12
        Integer sum = add2mult3.apply(arg);//INVOKE THE FUNCTION
        System.out.println("And Then:" + sum);
        return sum;
    }
}
