package com.lhduc.exceptions;

public class NotFoundException extends ApplicationRuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String prefix, int id) {
        super("*** ERROR: " + prefix + " with id = " + id + " was not found!");
    }
}
