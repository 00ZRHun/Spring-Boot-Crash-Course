package com.example.demo.testingRestControllerAdvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testingRestControllerAdvice")
public class controller {

    @Autowired
    private Service service;

    @GetMapping("/index")
    public void index() {
        //throw new IllegalStateException("HERE1: IllegalStateException message");
        service.index();
    }

}
