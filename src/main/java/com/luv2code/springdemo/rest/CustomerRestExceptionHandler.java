package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Global exception handler/interceptor
public class CustomerRestExceptionHandler {

    //add an exception handler for CustomerNotFoundException
    //when this type of exception occurs, this class will intercept and return
    //CustomerErrorResponse entity in JSON
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){

        //create customerErrorResponse
        CustomerErrorResponse response = new CustomerErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimeStamp(System.currentTimeMillis());
        response.setMessage(exc.getMessage());

        //return entity
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //add another handler for any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception exc){

        //create customerErrorResponse
        CustomerErrorResponse response = new CustomerErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimeStamp(System.currentTimeMillis());
        response.setMessage(exc.getMessage());

        //return entity
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
