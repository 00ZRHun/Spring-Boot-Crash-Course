package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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
