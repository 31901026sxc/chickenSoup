package com.example.chickensoup.exception;

public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
