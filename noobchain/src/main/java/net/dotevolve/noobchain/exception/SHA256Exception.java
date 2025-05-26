package net.dotevolve.noobchain.exception;

import java.io.Serial;

public class SHA256Exception extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public SHA256Exception(String message) {
        super(message);
    }

    public SHA256Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public SHA256Exception(Throwable cause) {
        super(cause);
    }
}
