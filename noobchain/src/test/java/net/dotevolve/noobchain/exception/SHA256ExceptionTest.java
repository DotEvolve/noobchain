package net.dotevolve.noobchain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SHA256ExceptionTest {

    @Test
    @DisplayName("Constructor with message sets correct message")
    void constructorWithMessageSetsCorrectMessage() {
        String message = "Error occurred";
        SHA256Exception exception = new SHA256Exception(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with message and cause sets correct message and cause")
    void constructorWithMessageAndCauseSetsCorrectMessageAndCause() {
        String message = "Error occurred";
        Throwable cause = new RuntimeException("Cause");
        SHA256Exception exception = new SHA256Exception(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Constructor with cause sets correct cause")
    void constructorWithCauseSetsCorrectCause() {
        Throwable cause = new RuntimeException("Cause");
        SHA256Exception exception = new SHA256Exception(cause);
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Cause", exception.getMessage());
    }
}
