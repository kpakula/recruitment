package com.kpakula.recruitment.exception;

public class FileProcessingException extends RuntimeException {
    public FileProcessingException() {
        super("File can not be created");
    }
}
