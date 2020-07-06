package com.course.calypso.java9.functional;



/*
     The other benefit to adding the @FunctionalInterface annotation is that it generates a statement in the Javadocs as follows:
     Functional Interface:
     This is a functional interface and can therefore be used as the assignment target for a lambda expression or method reference.
     Functional interfaces can have default and static methods as well.
     Both default and static methods have implementations, so they don’t count against
     the single abstract method requirement.
*/
@FunctionalInterface
public interface MyInterface {

    int myMethod(); //Single Abstract Method

    default String sayHello() {
        return "Hello, World!";
    }

    static void myStaticMethod() {
        System.out.println("I'm a static method in an interface");
    }
}