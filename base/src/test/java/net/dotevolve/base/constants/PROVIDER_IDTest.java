package net.dotevolve.base.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PROVIDER_IDTest {

    @Test
    void enumValuesShouldHaveCorrectStringValue() {
        assertEquals("123", PROVIDER_ID.TEST.getValue());
    }

    @Test
    void enumValuesShouldNotBeNull() {
        for (PROVIDER_ID providerId : PROVIDER_ID.values()) {
            assertNotNull(providerId.getValue());
        }
    }
}