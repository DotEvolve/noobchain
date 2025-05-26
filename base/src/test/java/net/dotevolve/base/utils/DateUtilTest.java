/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.util.Pair;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import static org.junit.Assert.*;

public class DateUtilTest {

    private DateUtil dateUtil;

    @Before
    public void setUp() {
        dateUtil = new DateUtil();
        // Fix the current time to ensure consistent test results
        DateTimeUtils.setCurrentMillisFixed(new DateTime(2023, 5, 15, 10, 30, 0).getMillis());
    }

    @Test
    public void testGetCurrentTimeInMillis() {
        // Arrange - already done in setUp()

        // Act
        long currentTimeMillis = DateUtil.getCurrentTimeInMillis();

        // Assert
        assertEquals(new DateTime(2023, 5, 15, 10, 30, 0).getMillis(), currentTimeMillis);
    }

    @Test
    public void testGetCurrentDateTime() {
        // Arrange - already done in setUp()

        // Act
        String dateTimeString = dateUtil.getCurrentDateTime(DateTimeFormatEnum.FULL_DATE_TIME);

        // Assert
        // Use contains to check for the date part without timezone dependency
        assertTrue(dateTimeString.contains("2023-05-15T10:30:00"));
    }

    @Test
    public void testConvertFormat() {
        // Arrange
        String originalDateTime = "2023-05-15T10:30:00+0000";

        // Act
        String convertedDateTime = dateUtil.convertFormat(
            originalDateTime, 
            DateTimeFormatEnum.FULL_DATE_TIME, 
            DateTimeFormatEnum.TILL_DAY_HYPHEN
        );

        // Assert
        assertEquals("05-15-2023", convertedDateTime);
    }

    @Test
    public void testConvertFormatWithUTCFormat() {
        // Arrange
        String originalDateTime = "2023-05-15T10:30:00+0000";

        // Act
        String convertedDateTime = dateUtil.convertFormatWithUTCFormat(
            originalDateTime, 
            DateTimeFormatEnum.FULL_DATE_TIME, 
            DateTimeFormatEnum.FULL_DATE_TIME_ISO_MS
        );

        // Assert
        assertEquals("2023-05-15T10:30:00.000Z", convertedDateTime);
    }

    @Test
    public void testGetMillis() {
        // Arrange
        DateTime dateTime = new DateTime(2023, 5, 15, 10, 30, 0);
        String dateTimeString = dateTime.toString(DateTimeFormatEnum.FULL_DATE_TIME.toString());

        // Act
        long millis = dateUtil.getMillis(dateTimeString, DateTimeFormatEnum.FULL_DATE_TIME);

        // Assert
        // Use the same DateTime object to generate both the input string and expected millis
        assertEquals(dateTime.getMillis(), millis);
    }

    @Test
    public void testMiliSecondsToFormattedDateTime() {
        // Arrange
        long millis = new DateTime(2023, 5, 15, 10, 30, 0).getMillis();

        // Act
        String formattedDateTime = dateUtil.miliSecondsToFormattedDateTime(
            millis, 
            DateTimeFormatEnum.TILL_DAY_SLASH
        );

        // Assert
        assertEquals("5/15/2023", formattedDateTime);
    }

    @Test
    public void testSubtractDayFromCurrentDateWithStartAndEndTime() {
        // Arrange - already done in setUp()

        // Act
        Pair<Long, Long> result = dateUtil.subtractDayFromCurrentDateWithStartAndEndTime(1);

        // Assert
        DateTime yesterday = new DateTime(2023, 5, 14, 0, 0, 0);
        assertEquals(yesterday.millisOfDay().withMaximumValue().getMillis(), result.getFirst().longValue());
        assertEquals(yesterday.millisOfDay().withMinimumValue().getMillis(), result.getSecond().longValue());
    }

    @Test
    public void testTimeDiffInYears() {
        // Arrange
        String startTime = "2020-05-15T10:30:00.000Z";
        String endTime = "2023-05-15T10:30:00.000Z";

        // Act
        int yearsDiff = dateUtil.timeDiffInYears(startTime, endTime);

        // Assert
        assertEquals(3, yearsDiff);
    }

    @Test
    public void testTimeDiffInYearsWithPartialYear() {
        // Arrange
        String startTime = "2020-05-15T10:30:00.000Z";
        String endTime = "2023-01-01T10:30:00.000Z";

        // Act
        int yearsDiff = dateUtil.timeDiffInYears(startTime, endTime);

        // Assert
        // Should be 2 years since it's not a full 3 years yet
        assertEquals(2, yearsDiff);
    }
}
