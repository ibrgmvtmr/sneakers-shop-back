package edu.alatoo.sneakers.exception;

public class UsernameAlreadyExistException extends RuntimeException {
    public UsernameAlreadyExistException(String msg) {
        super(msg);
    }
}
