package com.lhduc.exception;

public class NotFoundException extends ApplicationRuntimeException {
    public NotFoundException(String prefix, int id) {
        super("❌ ERROR: " + prefix + " with id = " + id + " was not found!");
    }
}
