/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CodeConditionTest {

    @Test
    public void testValidate_ConditionTrue() {
        // Arrange
        boolean condition = true;
        String errorMessage = "This error should not be thrown";
        
        // Act & Assert
        // No exception should be thrown when condition is true
        CodeCondition.validate(condition, errorMessage);
        
        // If we reach here, the test passes
        assertTrue(true);
    }

    @Test(expected = RuntimeException.class)
    public void testValidate_ConditionFalse() {
        // Arrange
        boolean condition = false;
        String errorMessage = "Expected error message";
        
        // Act
        // This should throw a RuntimeException
        CodeCondition.validate(condition, errorMessage);
        
        // Assert is handled by the expected = RuntimeException.class annotation
    }

    @Test
    public void testValidate_ErrorMessageContent() {
        // Arrange
        boolean condition = false;
        String errorMessage = "Expected error message";
        
        try {
            // Act
            CodeCondition.validate(condition, errorMessage);
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            // Assert
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void testValidate_WithObjects() {
        // Arrange
        boolean condition = true;
        String errorMessage = "This error should not be thrown";
        Object[] objects = new Object[] { "object1", 123, true };
        
        // Act & Assert
        // No exception should be thrown when condition is true, even with additional objects
        CodeCondition.validate(condition, errorMessage, objects);
        
        // If we reach here, the test passes
        assertTrue(true);
    }
}