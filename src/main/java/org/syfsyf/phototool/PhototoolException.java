package org.syfsyf.phototool;

public class PhototoolException extends Exception{
    public PhototoolException() {
    }

    public PhototoolException(String message) {
        super(message);
    }

    public PhototoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhototoolException(Throwable cause) {
        super(cause);
    }

    public PhototoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
