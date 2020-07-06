package com.course.calypso.java9.streams;

import com.course.calypso.dto.Book;
import com.course.calypso.dto.Employee;
import com.course.calypso.dto.Golfer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class ComparatorsAndCollectorsDemo {

    private List<Golfer> golfers = Arrays.asList(new Golfer("Jack", "Nicklaus", 68),
            new Golfer("Tiger", "Woods", 70),
            new Golfer("Tom", "Watson", 70),
            new Golfer("Ty", "Webb", 68),
            new Golfer("Bubba", "Watson", 70));

    /*
     * The golfers are sorted by score, so Nicklaus and Webb come before Woods and both Watsons.
     * 1 Then equal scores are sorted by last name, putting Nicklaus before Webb and Watson before Woods.
     * Finally, equal scores and last names are sorted by first name, putting Bubba Watson before Tom Watson.
     * */
    public List<Golfer> sortByScoreThenLastThenFirst() {
        return golfers.stream()
                .sorted(comparingInt(Golfer::getScore)
                        .thenComparing(Golfer::getLast)
                        .thenComparing(Golfer::getFirst))
                .collect(toList());
    }


    /*
    Both of these examples used the default data structures—ArrayList for List, and HashSet for Set.
    If you wish to specify a particular data structure, you should use the Collectors.toCollection method,
    which takes a Supplier as an argument.
    */
    public void covertStreamToCollect() {

        List<String> superHeroes =
                Stream.of("Mr. Furious", "The Blue Raja", "The Shoveler",
                        "The Bowler", "Invisible Boy", "The Spleen", "The Sphinx")
                        .collect(Collectors.toList());// default to ArrayList

        Set<String> villains =
                Stream.of("Casanova Frankenstein", "The Disco Boys",
                        "The Not-So-Goodie Mob", "The Suits", "The Suzies",
                        "The Furriers", "The Furriers")
                        .collect(Collectors.toSet()); // default to hashset

        List<String> actors =
                Stream.of("Hank Azaria", "Janeane Garofalo", "William H. Macy",
                        "Paul Reubens", "Ben Stiller", "Kel Mitchell", "Wes Studi")
                        .collect(Collectors.toCollection(LinkedList::new));// mention the data structure

    }

    public String[] convertToArray() {
        String[] wannabes =
                Stream.of("The Waffler", "Reverse Psychologist", "PMS Avenger")
                        .toArray(String[]::new);
        return wannabes;
    }

    public Map<Integer, String> convertSetToMap(Set<Book> bookSet) {

        Map<Integer, String> actorMap = bookSet.stream()
                .collect(Collectors.toMap(Book::getId, Book::getName));

        actorMap.forEach((key, value) ->
                System.out.printf("%s =  %s%n", key, value));
        return actorMap;
    }

    /*
    *  The static identity method in Function has the signature static <T> Function<T,T> identity()
        The implementation in the standard library is shown in Example 4-16. Example 4-16. The static identity
        *  in Function static <T> Function<T, T> identity() { return t -> t;
        }
    * */
    public Map<Integer, Book> convertSetToStaticIdentity(Set<Book> books) {

        //1. Identity lambda: given an element, return it
        Map<Integer, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, b -> b));
        return bookMap;
    }

    public Map<Integer, Book> convertSetToMapFunctionIdentity(Set<Book> books) {

        //2. Static identity method in Function does the same thing
        Map<Integer, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, Function.identity()));
        bookMap.forEach((key, value) -> System.out.printf("%s =  %s%n", key, value));
        return bookMap;
    }

    /*
     The Collectors.partitioningBy method splits elements into those that satisfy a Predicate and those that do not.

     */
    public Map<Boolean, List<String>> convertListToMapByPartition() {
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, List<String>> lengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));
        lengthMap.forEach((key, value) -> System.out.printf("%5s: %s%n", key, value));
        return lengthMap;
    }

    /*
     The Collectors.groupingBy method produces a Map of categories, where the values are the elements in each
     category.
     */
    public Map<Integer, List<String>> convertListToMapGroupBy() {
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Integer, List<String>> lengthMap = strings.stream()
                .collect(Collectors.groupingBy(String::length)); //Grouping strings by length
        lengthMap.forEach((k, v) -> System.out.printf("%d: %s%n", k, v));
        return lengthMap;
    }

    /*
    * This is called a downstream collector, because it is postprocessing the resulting lists downstream (i.e., after
    * the partitioning operation is completed).
    * */
    public Map<Boolean, Long> convertListToMapByGroupByAndTotalCount(){

        //Counting the partitioned strings
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, Long> numberLengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0,
                        Collectors.counting()));//downstreaming
        return numberLengthMap;
        // false: 4
        // true: 8
        // Downstream collector
    }

    /*
     The reduce method requires a BinaryOperator. The static maxBy method produces that BinaryOperator based on the
     supplied Comparator, which in this case compares employees by salary.
     */
    public void employeeListOperatorMaxBy(){

        List<Employee> employees = Arrays.asList(
                        new Employee("Cersei",250_000, "Lannister"),
                        new Employee("Jamie",  150_000, "Lannister"),
                        new Employee("Tyrion",1_000, "Lannister"),
                        new Employee("Tywin",  1_000_000, "Lannister"),
                        new Employee("Jon Snow",  75_000, "Stark"),
                        new Employee("Robb", 120_000, "Stark"),
                        new Employee("Eddard", 125_000, "Stark"),
                        new Employee("Sansa", 0, "Stark"),
                        new Employee("Arya",   1_000, "Stark"));

        Employee defaultEmployee =
                new Employee("A man (or woman) has no name", 0, "Black and White");
        //1:Binary Operator
        Optional<Employee> optionalEmp = employees.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(Employee::getSalary)));

        System.out.println("Emp with max salary: " + optionalEmp.orElse(defaultEmployee));

        //2:Comparator Employee
        Optional<Employee> optionalEmpByComparator = employees.stream()
                .max(Comparator.comparingInt(Employee::getSalary));
        System.out.println("The max salary by Comparator " + optionalEmpByComparator.get());

        //3:Max by IntStream
        OptionalInt maxSalary = employees.stream()
                .mapToInt(Employee::getSalary)
                .max();
        System.out.println("The max salary is " + maxSalary);
    }


}
