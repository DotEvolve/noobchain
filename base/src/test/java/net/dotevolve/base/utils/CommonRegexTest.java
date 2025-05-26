/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class CommonRegexTest {

    @Test
    public void testGetDateRegex() {
        // Arrange - nothing to arrange

        // Act
        Pattern dateRegex = CommonRegex.getDateRegex();

        // Assert
        assertNotNull(dateRegex);
        assertTrue(dateRegex.matcher("2023-05-15").matches());
        assertTrue(dateRegex.matcher("2023/05/15").matches());
        assertTrue(dateRegex.matcher("15-05-2023").matches());
        assertTrue(dateRegex.matcher("15/05/2023").matches());
    }

    @Test
    public void testMatchDateString_ValidDates() {
        // Arrange - nothing to arrange

        // Act & Assert
        assertTrue(CommonRegex.matchDateString("2023-05-15"));
        assertTrue(CommonRegex.matchDateString("2023/05/15"));
        assertTrue(CommonRegex.matchDateString("15-05-2023"));
        assertTrue(CommonRegex.matchDateString("15/05/2023"));
    }

    @Test
    public void testMatchDateString_InvalidDates() {
        // Arrange - nothing to arrange

        // These dates should be invalid according to the regex pattern
        String[] invalidDates = {
            "2023-5-15",    // Missing leading zero
            "2023-05-5",    // Missing leading zero
            "2023.05.15",   // Wrong separator
            "not a date",
            "",
            "20230515"      // No separators
        };

        for (String date : invalidDates) {
            boolean result = CommonRegex.matchDateString(date);
            assertFalse("Date should be invalid: " + date, result);
        }
    }

    @Test
    public void testMatchDateString_ValidDDMMYYYY() {
        // Arrange - nothing to arrange

        // Act & Assert
        // The regex accepts DD-MM-YYYY format where DD and MM can be 01-31
        // Note: The regex doesn't validate that MM is 01-12 or that DD is valid for the month
        assertTrue(CommonRegex.matchDateString("01-05-2023"));
        assertTrue(CommonRegex.matchDateString("15-05-2023"));
        assertTrue(CommonRegex.matchDateString("23-05-2023"));
        assertTrue(CommonRegex.matchDateString("31-05-2023"));

        // This is technically MM-DD-YYYY in real usage, but the regex treats it as DD-MM-YYYY
        // where 05 is the day and 15 is the month
        assertTrue(CommonRegex.matchDateString("05-15-2023"));
    }

    @Test
    public void testMatchGender_ValidGenders() {
        // Arrange - nothing to arrange

        // Act & Assert
        assertTrue(CommonRegex.matchGender("male"));
        assertTrue(CommonRegex.matchGender("female"));
        assertTrue(CommonRegex.matchGender("MALE"));
        assertTrue(CommonRegex.matchGender("FEMALE"));
        assertTrue(CommonRegex.matchGender("Male"));
        assertTrue(CommonRegex.matchGender("Female"));
    }

    @Test
    public void testMatchGender_InvalidGenders() {
        // Arrange - nothing to arrange

        // Act & Assert
        assertFalse(CommonRegex.matchGender("m"));
        assertFalse(CommonRegex.matchGender("f"));
        assertFalse(CommonRegex.matchGender("M"));
        assertFalse(CommonRegex.matchGender("F"));
        assertFalse(CommonRegex.matchGender("other"));
        assertFalse(CommonRegex.matchGender(""));
        assertFalse(CommonRegex.matchGender("mal")); // Typo
        assertFalse(CommonRegex.matchGender("femal")); // Typo
    }
}
