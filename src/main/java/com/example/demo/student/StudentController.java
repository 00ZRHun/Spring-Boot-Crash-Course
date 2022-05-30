package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired  // dependency injection
    public StudentController(StudentService studentService) {
        // this.studentService = new StudentService();  // Work but X good, so use dependency injection
         this.studentService = studentService;  // dependency injection
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

}
