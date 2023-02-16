package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {

    /*@Bean
    CommandLineRunner commandLineRunner (
            StudentRepository studentRepository) {
        return arg -> {
            Student mariam = new Student(
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, JANUARY, 5)
            );

            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, JANUARY, 5)
            );

            studentRepository.saveAll(  // when we invoke saveAll(), hibernate is running
                    List.of(mariam, alex)
            );
        };
    }*/
//    args
//    [{"id":1,"name":"Mariam","email":"mariam.jamal@gmail.com","dob":"2000-01-05","age":21},{"id":1,"name":"Hun","email":"hunzurong@gmail.com","dob":"2000-01-01","age":21}]
//    args = [
//        mariam = {"id":1,"name":"Mariam","email":"mariam.jamal@gmail.com","dob":"2000-01-05","age":21};
//        alex = {"id":1,"name":"Hun","email":"hunzurong@gmail.com","dob":"2000-01-01","age":21}
//    ];



}
