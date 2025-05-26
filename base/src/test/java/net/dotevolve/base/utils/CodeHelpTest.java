/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CodeHelpTest {

    @Test
    public void testSplitCamelCase() {
        // Test with simple camel case
        assertEquals("Hello World", CodeHelp.splitCamelCase("helloWorld"));
        
        // Test with multiple words
        assertEquals("User Account Settings", CodeHelp.splitCamelCase("userAccountSettings"));
        
        // Test with single word
        assertEquals("Hello", CodeHelp.splitCamelCase("hello"));
        
        // Test with already capitalized first letter
        assertEquals(" Hello World", CodeHelp.splitCamelCase("HelloWorld"));
    }

    @Test
    public void testIsEmpty() {
        // Test with null string
        assertTrue(CodeHelp.isEmpty((String) null));
        
        // Test with empty string
        assertTrue(CodeHelp.isEmpty(""));
        
        // Test with non-empty string
        assertFalse(CodeHelp.isEmpty("test"));
        
        // Test with whitespace string
        assertFalse(CodeHelp.isEmpty(" "));
    }

    @Test
    public void testNotEmpty() {
        // Test with null string
        assertFalse(CodeHelp.notEmpty(null));
        
        // Test with empty string
        assertFalse(CodeHelp.notEmpty(""));
        
        // Test with non-empty string
        assertTrue(CodeHelp.notEmpty("test"));
        
        // Test with whitespace string
        assertTrue(CodeHelp.notEmpty(" "));
    }

    @Test
    public void testIsEmptyList() {
        // Test with null list
        assertTrue(CodeHelp.isEmpty((List<?>) null));
        
        // Test with empty list
        assertTrue(CodeHelp.isEmpty(new ArrayList<>()));
        
        // Test with non-empty list
        assertFalse(CodeHelp.isEmpty(List.of("item")));
    }

    @Test
    public void testIsNotEmptyList() {
        // Test with null list
        assertFalse(CodeHelp.isNotEmpty(null));
        
        // Test with empty list
        assertFalse(CodeHelp.isNotEmpty(new ArrayList<>()));
        
        // Test with non-empty list
        assertTrue(CodeHelp.isNotEmpty(List.of("item")));
    }

    @Test
    public void testParseDouble() {
        // Test with valid number
        assertEquals(123.45, CodeHelp.parseDouble("123.45"), 0.001);
        
        // Test with currency format
        assertEquals(123.45, CodeHelp.parseDouble("$123.45"), 0.001);
        
        // Test with comma separator
        assertEquals(1234.56, CodeHelp.parseDouble("1,234.56"), 0.001);
        
        // Test with percentage
        assertEquals(12.34, CodeHelp.parseDouble("12.34%"), 0.001);
        
        // Test with empty string
        assertEquals(0.0, CodeHelp.parseDouble(""), 0.001);
        
        // Test with null
        assertEquals(0.0, CodeHelp.parseDouble(null), 0.001);
        
        // Test with invalid number
        assertEquals(0.0, CodeHelp.parseDouble("not a number"), 0.001);
    }

    @Test
    public void testIsNumeric() {
        // Test with valid integer
        assertTrue(CodeHelp.isNumeric("123"));
        
        // Test with valid decimal
        assertTrue(CodeHelp.isNumeric("123.45"));
        
        // Test with negative number
        assertTrue(CodeHelp.isNumeric("-123.45"));
        
        // Test with scientific notation
        assertTrue(CodeHelp.isNumeric("1.23e4"));
        
        // Test with non-numeric string
        assertFalse(CodeHelp.isNumeric("not a number"));
        
        // Test with null
        assertFalse(CodeHelp.isNumeric(null));
        
        // Test with empty string
        assertFalse(CodeHelp.isNumeric(""));
        
        // Test with alphanumeric string
        assertFalse(CodeHelp.isNumeric("123abc"));
    }

    @Test
    public void testToJson() {
        // Test with simple object
        String actualString = CodeHelp.toJson(Map.of("name", "John", "age", 30));
        String expectedValue1 = "{\"name\":\"John\",\"age\":30}";
        String expectedValue2 = "{\"age\":30,\"name\":\"John\"}";
        assertTrue(actualString.equals(expectedValue1) || actualString.equals(expectedValue2));
        
        // Test with null
        assertEquals("null", CodeHelp.toJson(null));
        
        // Test with empty object
        assertEquals("{}", CodeHelp.toJson(Map.of()));
        
        // Test with array
        assertEquals("[1,2,3]", CodeHelp.toJson(new int[]{1, 2, 3}));
    }

    @Test
    public void testTitleCase() {
        // Test with lowercase
        assertEquals("Hello World", CodeHelp.titleCase("hello world"));
        
        // Test with mixed case
        assertEquals("HELLO WORLD", CodeHelp.titleCase("hELLO wORLD"));
        
        // Test with single word
        assertEquals("Hello", CodeHelp.titleCase("hello"));
        
        // Test with empty string
        assertEquals("", CodeHelp.titleCase(""));
    }

    @Test
    public void testSanitizeName() {
        // Test with valid name
        assertEquals("John Doe", CodeHelp.sanitizeName("John Doe"));
        
        // Test with numbers
        assertEquals("John", CodeHelp.sanitizeName("John123"));
        
        // Test with special characters
        assertEquals("JohnDoe", CodeHelp.sanitizeName("John@Doe!"));
        
        // Test with apostrophe (should be preserved)
        assertEquals("O'Connor", CodeHelp.sanitizeName("O'Connor"));
        
        // Test with empty string
        assertEquals("", CodeHelp.sanitizeName(""));
    }
}