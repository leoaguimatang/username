package com.leo.exception;

public class UsernameException extends RuntimeException {
    private ErrorKey errorKey;

    /**
     * Constructs the exception.
     */
    public UsernameException(ErrorKey errorKey) {
        super(errorKey.getMessage());
        this.errorKey = errorKey;
    }

    /**
     * Returns the {@link ErrorKey} associated with this exception.
     */
    public ErrorKey getErrorKey() {
        return errorKey;
    }
}
