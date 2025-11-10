package com.publicsapient.publicsapient.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> myConstraintViolationException(ConstraintViolationException e)
    {
        Map<String,String> response=new HashMap<>();
        e.getConstraintViolations().forEach(err->{
            String param=err.getPropertyPath().toString();
            String message=err.getMessage();
            response.put(param,message);
        });
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String,String>> myMissingServletRequestParameterException(MissingServletRequestParameterException e)
    {
        Map<String,String> response=new HashMap<>();
        response.put(e.getParameterName(), e.getParameterName()+" is required");
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e)
    {
        String message=e.getMessage();
        System.out.println(message);
        return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
    }
}
