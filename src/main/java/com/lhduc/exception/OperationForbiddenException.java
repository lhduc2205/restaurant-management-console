package com.lhduc.exception;

public class OperationForbiddenException extends ApplicationRuntimeException {
    public OperationForbiddenException(String message) {
        super(message);
    }

}
