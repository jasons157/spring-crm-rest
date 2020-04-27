package com.luv2code.springdemo.rest;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String s) {
        super(s);
    }

    public CustomerNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CustomerNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public CustomerNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
