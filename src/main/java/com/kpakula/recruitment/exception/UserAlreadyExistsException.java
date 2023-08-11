package com.kpakula.recruitment.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String pesel) {
        super("User with pesel: " + pesel + " already exists");
    }
}
