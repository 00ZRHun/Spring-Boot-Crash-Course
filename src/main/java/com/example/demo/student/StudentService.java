package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service // annotation (Spring bean) // more semantics & high understandability // == @Component (both r exactly similar)
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();  // return a list
    }

    public void addNewStudent(Student student) {
        System.out.println(student);
    }
}
