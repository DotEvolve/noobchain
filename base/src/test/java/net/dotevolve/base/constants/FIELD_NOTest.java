package net.dotevolve.base.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FIELD_NOTest {

    @Test
    void enumValuesShouldHaveCorrectIdValue() {
        assertEquals(10, FIELD_NO.ID.getId());
        assertEquals(100, FIELD_NO.FIRST_NAME.getId());
        assertEquals(110, FIELD_NO.LAST_NAME.getId());
    }

    @Test
    void enumValuesShouldHaveCorrectStringValue() {
        assertEquals("id", FIELD_NO.ID.getValue());
        assertEquals("first_name", FIELD_NO.FIRST_NAME.getValue());
        assertEquals("last_name", FIELD_NO.LAST_NAME.getValue());
    }

    @Test
    void enumValuesShouldNotHaveNullValue() {
        for (FIELD_NO fieldNo : FIELD_NO.values()) {
            assertNotNull(fieldNo.getValue());
        }
    }

    @Test
    void staticConstantsShouldHaveCorrectValues() {
        assertEquals(120, FIELD_NO.GENDER);
        assertEquals(200, FIELD_NO.EMAIL);
        assertEquals(250, FIELD_NO.MOBILE);
        assertEquals(260, FIELD_NO.PHONE);
        assertEquals(300, FIELD_NO.ADDRESS);
        assertEquals(101010, FIELD_NO.WORKER);
        assertEquals(102010, FIELD_NO.CUSTOMER);
        assertEquals(103010, FIELD_NO.ARTICLE);
        assertEquals(104010, FIELD_NO.SIZE_10);
    }
}