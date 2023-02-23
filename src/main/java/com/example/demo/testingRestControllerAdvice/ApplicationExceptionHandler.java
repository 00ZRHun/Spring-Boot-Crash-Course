package com.example.demo.testingRestControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)   // OPT: HttpStatus.INTERNAL_SERVER_ERROR
    @ExceptionHandler(IllegalStateException.class)
    public Map<String, String> handleIllegalStateException(IllegalStateException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

}
