package net.dotevolve.base.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CommonRegex {

    public static Pattern getDateRegex() {
        String DATE_REGEX = "(\\d{4}[-/]\\d{2}[-/]\\d{2})|(\\d{2}[-/]\\d{2}[-/]\\d{4})";
        return Pattern.compile(DATE_REGEX);
    }

    public static boolean matchDateString(String inputString) {
        return getDateRegex().matcher(inputString).matches();
    }

    public static boolean matchGender(String inputString) {
        if (inputString.equalsIgnoreCase("male")) {
            return true;
        }
        return inputString.equalsIgnoreCase("female");
    }

}
