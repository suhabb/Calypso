package com.course.calypso.java9.streams;

import com.course.calypso.dto.Book;
import com.course.calypso.dto.Golfer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComparatorsAndCollectorsDemoTest {

    private ComparatorsAndCollectorsDemo comparatorsAndCollectorsDemo = new ComparatorsAndCollectorsDemo();

    @Test
    public void given_list_sort_by_compare() {
        List<Golfer> golferList = comparatorsAndCollectorsDemo.sortByScoreThenLastThenFirst();
        List<Golfer> expectedGolferList = Arrays.asList(
                new Golfer("Jack", "Nicklaus", 68),//sort by score,LastName,FirstName
                new Golfer("Ty", "Webb", 68),
                new Golfer("Bubba", "Watson", 70),
                new Golfer("Tom", "Watson", 70),
                new Golfer("Tiger", "Woods", 70));

        Assert.assertEquals(expectedGolferList, golferList);

    }

    @Test
    public void given_stream_convert_to_list() {
        comparatorsAndCollectorsDemo.covertStreamToCollect();

    }

    @Test
    public void given_stream_convert_to_array() {
        String[] expected = {"The Waffler", "Reverse Psychologist", "PMS Avenger"};
        String[] toArray = comparatorsAndCollectorsDemo.convertToArray();
        Assert.assertArrayEquals(toArray, expected);

    }

    @Test
    public void given_stream_set_convert_to_set() {

        Set<Book> bookSet = Set.of(Book.builder().name("Steve Jobs").id(1).build(),
                Book.builder().name("Rage Of Angels").id(2).build());
        Map<Integer, String> actualMap = comparatorsAndCollectorsDemo.convertSetToMap(bookSet);
        Assert.assertEquals("Steve Jobs", actualMap.get(1));
    }

    @Test
    public void given_stream_set_convert_to_Map() {

        Set<Book> bookSet = Set.of(Book.builder().name("Steve Jobs").id(1).build(),
                Book.builder().name("Rage Of Angels").id(2).build());
        Map<Integer, Book> actualMap = comparatorsAndCollectorsDemo.convertSetToStaticIdentity(bookSet);
        Assert.assertEquals("Steve Jobs", actualMap.get(1).getName());
    }

    @Test
    public void given_stream_set_convert_to_Map_function_identity() {

        Set<Book> bookSet = Set.of(Book.builder().name("Steve Jobs").id(1).build(),
                Book.builder().name("Rage Of Angels").id(2).build());
        Map<Integer, Book> actualMap = comparatorsAndCollectorsDemo.convertSetToMapFunctionIdentity(bookSet);
        Assert.assertEquals("Steve Jobs", actualMap.get(1).getName());
    }

    @Test
    public void given_convert_list_to_map_by_partition() {
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, List<String>> map = comparatorsAndCollectorsDemo.convertListToMapByPartition();
        Assert.assertEquals("a", map.get(false).get(0));// odd length of words
        Assert.assertEquals("is", map.get(true).get(1));// even length of words
    }


    @Test
    public void given_convert_list_to_map_by_group_by() {
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Integer, List<String>> map = comparatorsAndCollectorsDemo.convertListToMapGroupBy();
        Assert.assertEquals("a", map.get(1).get(0));// return the word with length 1
        Assert.assertEquals("of", map.get(2).get(1));// return the words with length 2
    }

    @Test
    public void convert_list_to_map_by_group_by_and_TotalCount(){
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, Long> map = comparatorsAndCollectorsDemo.convertListToMapByGroupByAndTotalCount();
        Assert.assertEquals(4, map.get(false).intValue());// odd length of words
        Assert.assertEquals(8, map.get(true).intValue());// even length of words
    }

    @Test
    public void given_employee_list_operator_MaxBy(){
        comparatorsAndCollectorsDemo.employeeListOperatorMaxBy();
    }
}
