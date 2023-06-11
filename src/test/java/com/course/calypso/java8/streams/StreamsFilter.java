package com.course.calypso.java8.streams;

import com.course.calypso.java8.streams.data.Student;
import com.course.calypso.java8.streams.data.StudentDataBase;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamsFilter {

    @Test
    public void testFilterStream(){
        List<Student> studentList = StudentDataBase.getAllStudents();

        List<Student> filterList = studentList.stream()
                .filter(s -> s.getGpa() > 2.0)
                .peek(s->{
                    System.out.println("StreamsFilter.testFilterStream:"+s.getGpa());
                })
                .collect(Collectors.toList());
        filterList.forEach(System.out::println);
    }
}
