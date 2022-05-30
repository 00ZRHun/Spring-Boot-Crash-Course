package com.example.demo.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service // annotation (Spring bean) // more semantics & high understandability // == @Component (both r exactly similar)
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Mariam",
                        "mariam.jamal@gmail.com",
                        LocalDate.of(2000, Month.JANUARY, 5),
                        21
                ),
                new Student(
                        1L,
                        "Hun",
                        "hunzurong@gmail.com",
                        LocalDate.of(2000, Month.JANUARY, 1),
                        21
                )
        );
    }

}
