/******************************************************************
 *
 * @copyright 2022 DotEvolve
 * @author
 * @since
 ******************************************************************/
package net.dotevolve.base.utils;

public enum DateTimeFormatEnum {
    FULL_DATE_TIME {
        public String toString() {
            return "yyyy-MM-dd'T'HH:mm:ssZ";
        }

    },
    FULL_DATE_TIME_ISO {
        public String toString() {
            return "yyyy-MM-dd'T'HH:mm:ssZZ";
        }

    },
    FULL_DATE_TIME_ISO_MS {
        public String toString() {
            return "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        }

    },
    FULL_DATE_TIME_AWS {
        public String toString() {
            return "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        }

    },
    TILL_MINUTE {
        public String toString() {
            return "YYYYMMddHHmm";
        }

    },
    TILL_SECOND {
        public String toString() {
            return "YYYYMMddHHmmss";
        }
    },
    TILL_HOUR {
        public String toString() {
            return "YYYYMMddHH";
        }

    },
    TILL_DAY {
        public String toString() {
            return "YYYYMMdd";
        }

    },
    TILL_DAY_START_YEAR {
        public String toString() {
            return "YYYY-MM-dd";
        }
    },
    TILL_DAY_HYPHEN {
        public String toString() {
            return "MM-dd-YYYY";
        }

    },
    TILL_MONTH_DAY_HYPHEN {
        public String toString() {
            return "dd-MM-YYYY";
        }

    },
    TILL_DAY_SLASH {
        public String toString() {
            return "M/d/YYYY";
        }

    },
    TILL_MONTH {
        public String toString() {
            return "YYYYMM";
        }

    },
    TILL_MONTH_HYPHEN {
        public String toString() {
            return "YYYY-MM";
        }
    },
    TILL_WEEK {
        public String toString() {
            return "YYYYww";
        }

    },
    ONLY_MINUTE {
        public String toString() {
            return "mm";
        }

    },
    ONLY_DAY_HOUR_MINUTE {
        public String toString() {
            return "ddHHmm";
        }

    },
    MONTH_DAY_TIME_FOR_DISPLAY {
        public String toString() {
            return "E,dd MMM HH:mma";
        }

    },
    ONLY_HOUR_MINUTE {
        public String toString() {
            return "HHmm";
        }

    },
    ONLY_WEEK_DAY_HOUR_MINUTE {
        public String toString() {
            return "eHHmm";
        }

    },
    ONLY_YEAR {
        public String toString() {
            return "YYYY";
        }

    },
    ONLY_DAY {
        public String toString() {
            return "dd";
        }

    },
    ONLY_MONTH {
        public String toString() {
            return "MMM";
        }

    },
    ONLY_SECOND {
        public String toString() {
            return "ss";
        }

    }

}