package com.course.calypso.java9.factory;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public interface SumNumbers {

    default int addEvens(int... nums) {
        return add(n -> n % 2 == 0, nums);
    }

    default int addOdds(int... nums) {
        return add(n -> n % 2 != 0, nums);
    }

    /*
    In Java SE 8, for the first time developers could add implementations to interface methods,
    labeling them as default or static. The next logical step was to add private methods as well.
    */
    private int add(IntPredicate predicate, int... nums) {
        return IntStream.of(nums)
                .filter(predicate)
                .sum();
    }
}