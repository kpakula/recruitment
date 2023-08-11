package com.kpakula.recruitment.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String pesel) {
        super("Could not find user with pesel: " + pesel);
    }
}
