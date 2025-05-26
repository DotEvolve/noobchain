package net.dotevolve.noobchain.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import net.dotevolve.noobchain.exception.SHA256Exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    @DisplayName("ApplySha256 returns correct hash for valid input")
    void applySha256ReturnsCorrectHash() throws SHA256Exception {
        String input = "test";
        String expectedHash = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";
        assertEquals(expectedHash, StringUtil.applySha256(input));
    }

    @Test
    @DisplayName("ApplySha256 throws SHA256Exception for null input")
    void applySha256ThrowsExceptionForNullInput() {
        assertThrows(SHA256Exception.class, () -> StringUtil.applySha256(null));
    }

    @Test
    @DisplayName("GetJson returns pretty-printed JSON for valid object")
    void getJsonReturnsPrettyPrintedJson() {
        Object obj = new Object() {
            public final String name = "test";
            public final int value = 123;
        };
        String expectedJson = "{\n  \"name\": \"test\",\n  \"value\": 123\n}";
        assertEquals(expectedJson, StringUtil.getJson(obj));
    }

    @Test
    @DisplayName("GetDificultyString returns correct string for positive difficulty")
    void getDificultyStringReturnsCorrectString() {
        int difficulty = 5;
        String expectedString = "00000";
        assertEquals(expectedString, StringUtil.getDificultyString(difficulty));
    }

    @Test
    @DisplayName("GetDificultyString returns empty string for zero difficulty")
    void getDificultyStringReturnsEmptyStringForZeroDifficulty() {
        int difficulty = 0;
        String expectedString = "";
        assertEquals(expectedString, StringUtil.getDificultyString(difficulty));
    }

    @Test
    @DisplayName("GetDificultyString throws NegativeArraySizeException for negative difficulty")
    void getDificultyStringThrowsExceptionForNegativeDifficulty() {
        int difficulty = -1;
        assertThrows(NegativeArraySizeException.class, () -> StringUtil.getDificultyString(difficulty));
    }
}
