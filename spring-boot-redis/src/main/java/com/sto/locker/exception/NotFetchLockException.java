package com.sto.locker.exception;


public class NotFetchLockException extends Exception {

    private static final long serialVersionUID = -6490056205468921965L;

    public NotFetchLockException(String message) {
        super(message);
    }
}