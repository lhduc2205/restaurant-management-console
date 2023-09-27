package com.lhduc.exception;

public class ForceExitApplicationException extends ApplicationRuntimeException {
    public ForceExitApplicationException() {
    }

    public ForceExitApplicationException(String message) {
        super(message);
    }

    public ForceExitApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForceExitApplicationException(Throwable cause) {
        super(cause);
    }

    public ForceExitApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
