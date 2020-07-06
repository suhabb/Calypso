package com.course.calypso.java9.factory;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

public class CreateCollectionTest {

    private CreateCollection createCollection = new CreateCollection();

    //Demonstrating immutability
    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilityAdd() throws Exception {
        createCollection.showImmutabilityAdd();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilityClear() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilityRemove() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.remove(0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilityReplace() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.replaceAll(n -> -n);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void showImmutabilitySet() throws Exception {
        List<Integer> intList = List.of(1, 2, 3);
        intList.set(0, 99);
    }


    @Test
    public void ofNullable()  {
        Stream<String> stream = Stream.ofNullable("abc");
        Assert.assertEquals(1, stream.count());  //Stream with one element

        stream = Stream.ofNullable(null);
        Assert.assertEquals(0, stream.count());  //Returns Stream.empty()
    }

    @Test
    public void given_list_drop_while() throws Exception {
        createCollection.dropWhile();
    }

    @Test
    public void given_list_take_while() throws Exception {
        createCollection.takeWhile();


    }
}

