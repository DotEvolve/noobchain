package net.dotevolve.base.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CONFIG_IDTest {

    @Test
    void enumValuesShouldHaveCorrectStringValue() {
        assertEquals("articlesList", CONFIG_ID.ARTICLES_LIST.getValue());
    }

    @Test
    void toStringShouldReturnFormattedString() {
        assertEquals("CONFIG_ID{value='articlesList'}", CONFIG_ID.ARTICLES_LIST.toString());
    }

    @Test
    void enumValuesShouldNotBeNull() {
        for (CONFIG_ID configId : CONFIG_ID.values()) {
            assertNotNull(configId.getValue());
        }
    }
}