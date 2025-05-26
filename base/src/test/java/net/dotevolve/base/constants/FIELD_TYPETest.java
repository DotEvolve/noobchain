package net.dotevolve.base.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FIELD_TYPETest {

    @Test
    void enumValuesShouldHaveCorrectStringValue() {
        assertEquals("text", FIELD_TYPE.TEXT.getValue());
        assertEquals("number", FIELD_TYPE.NUMBER.getValue());
        assertEquals("date", FIELD_TYPE.DATE.getValue());
        assertEquals("email", FIELD_TYPE.EMAIL.getValue());
        assertEquals("password", FIELD_TYPE.PASSWORD.getValue());
        assertEquals("hidden", FIELD_TYPE.HIDDEN.getValue());
    }

    @Test
    void enumValuesShouldNotBeNull() {
        for (FIELD_TYPE fieldType : FIELD_TYPE.values()) {
            assertNotNull(fieldType.getValue());
        }
    }
}