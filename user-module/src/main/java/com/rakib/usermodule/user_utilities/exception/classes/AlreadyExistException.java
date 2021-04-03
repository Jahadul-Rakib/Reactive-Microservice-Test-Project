package com.rakib.usermodule.user_utilities.exception.classes;

public class AlreadyExistException extends Exception {
    private static final long serialVersionUID = 1L;
    public AlreadyExistException(String message) {
        super(message);
    }
}
