package com.example.demo.testingRestControllerAdvice;

import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private StudentRepository studentRepository;

    public void index() {
        //throw new IllegalStateException("HERE2: IllegalStateException message");
        studentRepository.findById(0l)
                .orElseThrow(() -> new IllegalStateException("HERE3: IllegalStateException message"));
    }

}
