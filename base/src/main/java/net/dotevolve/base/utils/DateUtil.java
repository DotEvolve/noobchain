/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.utils;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Component
public class DateUtil {

    public static long getCurrentTimeInMillis() {
        return new DateTime().getMillis();
    }

    public String getCurrentDateTime(DateTimeFormatEnum format) {
        DateTime time = new DateTime();
        return time.toString(DateTimeFormat.forPattern(format.toString()));
    }

    public String convertFormat(String dateTime, DateTimeFormatEnum oldFormat, DateTimeFormatEnum newFormat) {
        DateTimeFormatter oldFormatter = DateTimeFormat.forPattern(oldFormat.toString());
        DateTime temp = oldFormatter.parseDateTime(dateTime);
        DateTimeFormatter newFormatter = DateTimeFormat.forPattern(newFormat.toString());
        return newFormatter.print(temp);
    }

    public String convertFormatWithUTCFormat(String dateTime, DateTimeFormatEnum oldFormat,
                                             DateTimeFormatEnum newFormat) {
        DateTimeFormatter oldFormatter = DateTimeFormat.forPattern(oldFormat.toString());
        DateTime temp = oldFormatter.parseDateTime(dateTime);
        DateTimeFormatter newFormatter = DateTimeFormat.forPattern(newFormat.toString()).withZoneUTC();
        return newFormatter.print(temp);
    }

    public long getMillis(String dateTime, DateTimeFormatEnum format) {
        return DateTimeFormat.forPattern(format.toString()).parseDateTime(dateTime).getMillis();
    }

    public String miliSecondsToFormattedDateTime(long milies, DateTimeFormatEnum format) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format.toString());
        DateTime dt = new DateTime(milies);
        return formatter.print(dt);
    }

    public Pair<Long, Long> subtractDayFromCurrentDateWithStartAndEndTime(int day) {
        return Pair.of(DateTime.now().minusDays(day).millisOfDay().withMaximumValue().getMillis(),
                DateTime.now().minusDays(day).millisOfDay().withMinimumValue().getMillis());
    }

    public int timeDiffInYears(String startTime, String endTime) {
        DateTime start = DateTime.parse(startTime);
        DateTime end = DateTime.parse(endTime);
        return Years.yearsBetween(start, end).getYears();
    }

}