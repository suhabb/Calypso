package com.course.calypso.java9.streams;

import org.junit.Test;

public class MethodReferenceDemoTest {

    private MethodReferenceDemo methodReferenceDemo = new MethodReferenceDemo();

    @Test
    public void accessPrintlnUsingMethodReference(){
        methodReferenceDemo.accessPrintlnUsingMethodReference();
    }

    @Test
    public void printRandomIntegersUsingMethodReference(){
        methodReferenceDemo.printRandomIntegersLimitToTen();
    }

    @Test
    public void methodReferenceAndEquivalentLambda() {
        methodReferenceDemo.methodReferenceAndEquivalentLambda();
    }
}
