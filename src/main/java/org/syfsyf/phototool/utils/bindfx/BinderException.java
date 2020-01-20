package org.syfsyf.phototool.utils.bindfx;

public class BinderException extends Exception {
    public BinderException() {
    }

    public BinderException(String message) {
        super(message);
    }

    public BinderException(String message, Throwable cause) {
        super(message, cause);
    }

    public BinderException(Throwable cause) {
        super(cause);
    }

    public BinderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
