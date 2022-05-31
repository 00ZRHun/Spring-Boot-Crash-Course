package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired  // dependency injection
    public StudentController(StudentService studentService) {
        // this.studentService = new StudentService();  // Work but X good, so use dependency injection
         this.studentService = studentService;  // dependency injection: magically instantiate for us & inject into constructor -> ALl work
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);

        /*
        POST http://localhost:8080/api/v1/student
        Content-Type: application/json

        {
            "name":"Bilal",
            "email":"bilal.ahmed@gmail.com",
            "dob":"1995-12-17"
        }

        * Other rest client: Postman
        */
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(
            @PathVariable("studentId") long studentId) {
        studentService.deleteStudent(studentId);
    }

}
