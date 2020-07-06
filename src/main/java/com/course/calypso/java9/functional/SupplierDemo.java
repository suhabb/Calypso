package com.course.calypso.java9.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.function.DoubleSupplier;

@Slf4j
public class SupplierDemo {

    public void supplierFunction(){

        DoubleSupplier randomSupplier = new DoubleSupplier() {
                @Override
                public double getAsDouble() {
                return Math.random();
            }
        };
        randomSupplier = () -> Math.random();
        randomSupplier = Math::random;
        log.info("---Supplier Demo--- :{}",randomSupplier.getAsDouble());
    }
}
