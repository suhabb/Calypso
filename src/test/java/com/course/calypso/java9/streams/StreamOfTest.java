package com.course.calypso.java9.streams;

import com.course.calypso.dto.Customer;
import com.course.calypso.dto.Employee;
import com.course.calypso.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class StreamOfTest {

    private StreamOfDemo streamOfDemo = new StreamOfDemo();

    @Test
    public void given_string_of_array_return_string() {
        String names = streamOfDemo.streamCollectorsJoining();
        assertEquals("Gomez,Morticia,Wednesday,Pugsley", names);
    }

    @Test
    public void given_big_decimal_stream() {
        List<BigDecimal> decimals = streamOfDemo.findBigDecimalSum();
        assertThat(decimals, contains(new BigDecimal(1), new BigDecimal(2)));

    }

    @Test
    public void given_local_date_stream() {
        // prints 10 days starting from today
        List<LocalDate> localDates = Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1L))
                .limit(10)
                .collect(Collectors.toList());
        log.info("Local Date : {}", localDates.get(0));

    }

    @Test
    public void given_stream_boxed_inputs() {
        List<Integer> integerList = streamOfDemo.streamOfBoxedInts();
        assertThat(integerList, contains(10, 11, 12, 13, 14));
    }

    @Test
    public void given_stream_primitive_int_to_stream() {
        List<Integer> integerList = streamOfDemo.streamsOfPrimitiveIntToBoxedInt(1, 6, 3, 9, 6);
        assertThat(integerList, contains(1, 6, 3, 9, 6));
    }

    @Test
    public void given_stream_convert_int_to_array() {
        int[] intArray = streamOfDemo.convertToArray(3, 1, 4, 1, 5, 9);
        assertEquals(intArray[0], 3);
    }

    @Test
    public void given_int_find_first_even_element() {
        //{3, 1, 4, 1, 5, 9, 2, 6, 5};
        int even = streamOfDemo.findFirstEven();
        assertEquals(4, even);
    }

    @Test
    public void testIsPrimeUsingAllMatch() {
        assertTrue(IntStream.of(2, 3, 5, 7, 11, 13, 17, 19)
                .allMatch(StreamOfDemo::isPrime));
    }

    @Test
    public void testIsPrimeWithComposites() {
        assertFalse(Stream.of(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20)
                .anyMatch(StreamOfDemo::isPrime));// Use allMatch for simplicity
    }

    @Test
    public void given_map_stream_return_list() {
        Customer sheridan = new Customer("Sheridan");
        Customer ivanova = new Customer("Ivanova");
        Customer garibaldi = new Customer("Garibaldi");
        sheridan.addOrder(new Order(1)).addOrder(new Order(2))
                .addOrder(new Order(3));
        ivanova.addOrder(new Order(4))
                .addOrder(new Order(5));
        streamOfDemo.mapStream(Arrays.asList(sheridan, ivanova, garibaldi));
    }

    @Test
    public void given_flat_map_stream_return_list() {

        Customer sheridan = new Customer("Sheridan");
        Customer ivanova = new Customer("Ivanova");
        Customer garibaldi = new Customer("Garibaldi");
        sheridan.addOrder(new Order(1))
                .addOrder(new Order(2))
                .addOrder(new Order(3));
        ivanova.addOrder(new Order(4))
                .addOrder(new Order(5));
        streamOfDemo.flatStream(Arrays.asList(sheridan, ivanova, garibaldi));
    }

    @Test
    public void concat() throws Exception {
        List<String> stringList = Arrays.asList("a", "b", "c", "X", "Y", "Z");
        assertEquals(stringList, streamOfDemo.concat());
    }

    @Test
    public void concat_reduce() {

        List<String> strings = streamOfDemo.concatReduceStream();
        List<String> stringList = Arrays.asList("a", "b", "c",
                "X", "Y", "Z", "alpha", "beta", "gamma");
        assertEquals(stringList, strings);

    }

    @Test
    public void flat_map_stream_concat() {

        List<String> strings = streamOfDemo.flatMapStreamConcat();
        List<String> stringList = Arrays.asList("a", "b", "c",
                "X", "Y", "Z", "alpha", "beta", "gamma");
        assertEquals(stringList, strings);
    }

    @Test
    public void optional_return_employee_list() {
        List<Integer> salList = Arrays.asList(1000, 2000, 3000);
        List<Employee> actual = streamOfDemo.findEmployeesBySalary(salList);
        actual.stream().forEach(System.out::println);
        assertEquals(Integer.valueOf(1000), actual.get(0).getSalary());
    }

    @Test
    public void parallelStreamThenSequential() throws Exception {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertFalse(numbers.parallelStream()
                .sequential()
                .isParallel());
    }
}
