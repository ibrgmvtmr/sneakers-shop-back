package edu.alatoo.sneakers.exception;

public class EmailAlreadyExistException extends RuntimeException {

    public EmailAlreadyExistException(String msg) {
        super(msg);
    }
}
