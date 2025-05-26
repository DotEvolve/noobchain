/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ConversionUtilsTest {

    @InjectMocks
    private ConversionUtils conversionUtils;

    @Mock
    private DateUtil dateUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSat2act_HighSATScore() {
        // Arrange
        double satScore = 1580; // High SAT score
        double actScore = 0;    // No existing ACT score

        // Act
        double result = conversionUtils.satToAct(satScore, actScore);

        // Assert
        assertEquals(36, result, 0.001); // Should convert to the highest ACT score (36)
    }

    @Test
    public void testSat2act_MidSATScore() {
        // Arrange
        double satScore = 1200; // Mid-range SAT score
        double actScore = 0;    // No existing ACT score

        // Act
        double result = conversionUtils.satToAct(satScore, actScore);

        // Assert
        assertEquals(25, result, 0.001); // Should convert to corresponding ACT score
    }

    @Test
    public void testSat2act_LowSATScore() {
        // Arrange
        double satScore = 600; // Low SAT score
        double actScore = 0;   // No existing ACT score

        // Act
        double result = conversionUtils.satToAct(satScore, actScore);

        // Assert
        assertEquals(9, result, 0.001); // Should convert to corresponding ACT score
    }

    @Test
    public void testSat2act_ZeroSATScore() {
        // Arrange
        double satScore = 0;  // Zero SAT score
        double actScore = 0;  // No existing ACT score

        // Act
        double result = conversionUtils.satToAct(satScore, actScore);

        // Assert
        assertEquals(0, result, 0.001); // Should return 0
    }

    @Test
    public void testSatToActScore_Higher() {
        // Arrange
        double satScore = 1200; // Mid-range SAT score (converts to ~25)
        double actScore = 30;   // Higher existing ACT score

        // Act
        double result = conversionUtils.satToAct(satScore, actScore);

        // Assert
        assertEquals(30, result, 0.001); // Should keep the higher existing ACT score
    }

    @Test
    public void testSatToActScore_Lower() {
        // Arrange
        double satScore = 1400; // High-range SAT score (converts to ~31)
        double actScore = 25;   // Lower existing ACT score

        // Act
        double result = conversionUtils.satToAct(satScore, actScore);

        // Assert
        assertEquals(31, result, 0.001); // Should use the converted score since it's higher
    }

    @Test
    public void testClt2act_HighCLTScore() {
        // Arrange
        double cltScore = 115; // High CLT score
        double actScore = 0;   // No existing ACT score

        // Act
        double result = conversionUtils.cltToAct(cltScore, actScore);

        // Assert
        assertEquals(36, result, 0.001); // Should convert to the highest ACT score (36)
    }

    @Test
    public void testClt2act_MidCLTScore() {
        // Arrange
        double cltScore = 75; // Mid-range CLT score
        double actScore = 0;  // No existing ACT score

        // Act
        double result = conversionUtils.cltToAct(cltScore, actScore);

        // Assert
        assertEquals(23, result, 0.001); // Should convert to corresponding ACT score (75 is in the range [74, 76))
    }

    @Test
    public void testClt2act_LowCLTScore() {
        // Arrange
        double cltScore = 35; // Low CLT score
        double actScore = 0;  // No existing ACT score

        // Act
        double result = conversionUtils.cltToAct(cltScore, actScore);

        // Assert
        assertEquals(10, result, 0.001); // Should convert to corresponding ACT score
    }

    @Test
    public void testClt2act_ZeroCLTScore() {
        // Arrange
        double cltScore = 0; // Zero CLT score
        double actScore = 0; // No existing ACT score

        // Act
        double result = conversionUtils.cltToAct(cltScore, actScore);

        // Assert
        assertEquals(0, result, 0.001); // Should return 0
    }

    @Test
    public void testCltToActScore_Higher() {
        // Arrange
        double cltScore = 75; // Mid-range CLT score (converts to ~23)
        double actScore = 30; // Higher existing ACT score

        // Act
        double result = conversionUtils.cltToAct(cltScore, actScore);

        // Assert
        assertEquals(30, result, 0.001); // Should keep the higher existing ACT score
    }

    @Test
    public void testCltToActScore_Lower() {
        // Arrange
        double cltScore = 95; // High-range CLT score (converts to ~31)
        double actScore = 25; // Lower existing ACT score

        // Act
        double result = conversionUtils.cltToAct(cltScore, actScore);

        // Assert
        assertEquals(31, result, 0.001); // Should use the converted score since it's higher
    }

    @Test
    public void testDobToAge() {
        // Arrange
        String dob = LocalDate.now().minusYears(25).toString(); // 25 years ago

        // Act
        int age = conversionUtils.dobToAge(dob);

        // Assert
        assertEquals(25, age);
    }

    @Test
    public void testActToSat_HighACTScore() {
        // Arrange
        double actScore = 36; // Highest ACT score

        // Act
        double result = conversionUtils.actToSat(actScore);

        // Assert
        assertEquals(1590, result, 0.001); // Should convert to the highest SAT score range
    }

    @Test
    public void testActToSat_MidACTScore() {
        // Arrange
        double actScore = 24; // Mid-range ACT score

        // Act
        double result = conversionUtils.actToSat(actScore);

        // Assert
        assertEquals(1180, result, 0.001); // Should convert to corresponding SAT score
    }

    @Test
    public void testActToSat_LowACTScore() {
        // Arrange
        double actScore = 10; // Low ACT score

        // Act
        double result = conversionUtils.actToSat(actScore);

        // Assert
        assertEquals(630, result, 0.001); // Should convert to corresponding SAT score
    }

    @Test
    public void testActToSat_ZeroACTScore() {
        // Arrange
        double actScore = 0; // Zero ACT score

        // Act
        double result = conversionUtils.actToSat(actScore);

        // Assert
        assertEquals(400, result, 0.001); // Should return minimum SAT score
    }
}
