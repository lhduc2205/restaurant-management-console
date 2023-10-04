package com.lhduc.exception;

public class ResourceAlreadyExistsException extends ApplicationRuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
