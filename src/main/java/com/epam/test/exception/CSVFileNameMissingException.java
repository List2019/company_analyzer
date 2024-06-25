package com.epam.test.exception;

public class CSVFileNameMissingException extends RuntimeException {

    public CSVFileNameMissingException(String message) {
        super(message);
    }
}
