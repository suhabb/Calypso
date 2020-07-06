package com.course.calypso.java9.streams;

import com.course.calypso.dto.Customer;
import com.course.calypso.dto.Employee;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class StreamOfDemo {


    public String streamCollectorsJoining() {
        String names = Stream.of("Gomez", "Morticia", "Wednesday", "Pugsley")
                .collect(Collectors.joining(","));
        return names;
    }

    public List<BigDecimal> findBigDecimalSum() {

        List<BigDecimal> nums =
                Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                        .limit(2)
                        .collect(Collectors.toList());
        return nums;
    }

    public List<Integer> streamOfBoxedInts() {

        List<Integer> listOfInt = IntStream.range(10, 15)
                .boxed() //Necessary for Collectors to convert primitives to List<T>
                .collect(Collectors.toList());
        return listOfInt;
        //// prints [10, 11, 12, 13, 14]
    }

    public List<Integer> streamsOfPrimitiveIntToBoxedInt(int... arg) {

        List<Integer> listOfInt = IntStream.of(arg)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
        return listOfInt;
    }

    public int[] convertToArray(int... arg) {
        int[] intArray = IntStream.of(arg).toArray();
        // int[] intArray = IntStream.of(3, 1, 4, 1, 5, 9).toArray(int[]::new);
        return intArray;
        //prints 3, 1, 4, 1, 5, 9
    }

    public int findFirstEven() {

        Optional<Integer> oFirstEven = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .filter(n -> n % 2 == 0)
                .findFirst();
        log.info("Even :3, 1, 4, 1, 5, 9, 2, 6, 5 :  {}", oFirstEven.get());
        return oFirstEven.get();


    }

    public static boolean isPrime(int num) {
        int limit = (int) (Math.sqrt(num) + 1);
        log.info("Number :{} Limit : {}", num, limit);
        return num == 2 || num > 1 && IntStream.range(2, limit)
                .peek(n -> System.out.println(n))//Upper limit for check
                .noneMatch(divisor -> num % divisor == 0); // none match
        //Using anyMatch, allMatch, and noneMatch | 73 .noneMatch(divisor -> num % divisor == 0);
    }

    /*
   The signature for map is:
        <R> Stream<R> map(Function<? super T,? extends R> mapper)
        A Function takes a single input and transforms it into a single output. In the case of
        map, a single input of type T is transformed into a single output of type R.
        Consider a Customer class, where a customer has a name and a collection of Order. To keep things simple,
        the Order class just has an integer ID.
    *
    */
    public void mapStream(List<Customer> customers) {

        customers.stream()
                .map(Customer::getOrders)//Stream<List<Order>>
                .forEach(System.out::println);


        //print name
        customers.stream()
                .map(Customer::getName)//Stream<List<Order>>
                .forEach(System.out::println);


        customers.stream()
                .map(customer -> customer.getOrders().stream())//Stream<Stream<Order>>
                .forEach(System.out::println);

    }


    /*
    * The result of the flatMap operation is to produce a Stream<Order>, which has been flattened so you don’t need to worry about the nested streams any more.
        The two key concepts for flatMap are:
        • The Function argument to flatMap produces a Stream of output values.
        • The resulting stream of streams is flattened into a single stream of results.
    * */
    public void flatStream(List<Customer> customers) {

        customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .forEach(System.out::println);

    }


    public List<String> concat() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        // 3.12 Concatenating Streams
        Stream<String> second = Stream.of("X", "Y", "Z");
        List<String> strings = Stream.concat(first, second)
                .collect(Collectors.toList());
        return strings;
    }

    public List<String> concatReduceStream() {

        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");
        Stream<String> fourth = Stream.empty();


        //An alternative approach is to use the reduce method to perform multiple concatenations,
        List<String> strings = Stream.of(first, second, third, fourth)
                .reduce(Stream.empty(), Stream::concat)
                .collect(Collectors.toList());
        return strings;
    }

    public List<String> flatMapStreamConcat() {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");
        Stream<String> fourth = Stream.empty();

        List<String> strings = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
        return strings;
    }

    public List<Employee> findEmployeesBySalary(List<Integer> ids) {
        return ids.stream()
                .map(this::findEmployeeBySalary)//invoke this class method
                .filter(Optional::isPresent)// check if the employee exists
                .map(Optional::get) // get the employee
                .collect(Collectors.toList()); // add it in the list
    }

    private Optional<Employee> findEmployeeBySalary(Integer sal) {
        //return dummy employee
        return Optional.of(Employee.builder().salary(sal).name("steve").build());
    }

}
