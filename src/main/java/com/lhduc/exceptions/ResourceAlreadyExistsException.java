package com.lhduc.exceptions;

public class ResourceAlreadyExistsException extends ApplicationRuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
