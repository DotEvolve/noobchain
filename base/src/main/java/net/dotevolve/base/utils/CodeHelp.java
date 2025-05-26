/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 10/02/23, 2:35 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodeHelp {
    private static final Logger logger = LogManager.getLogger(CodeHelp.class);

    private CodeHelp() {}

    public static String splitCamelCase(String input) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String c = Character.toString(input.charAt(i));
            if (c.equals(c.toUpperCase())) {
                out.append(" ");
            }
            if (i == 0) {
                c = c.toUpperCase();
            }
            out.append(c);
        }
        return out.toString();
    }

    public static boolean isEmpty(String string) {
        if (string == null) {
            return true;
        }
        return string.isEmpty();
    }

    public static boolean notEmpty(String string) {
        return !isEmpty(string);
    }

    public static String USDNumberFormatting(int value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    public static String valueOfUSDFormattedNumber(String formattedNumber) {
        if (formattedNumber.contains("$")) {
            formattedNumber = formattedNumber.replace("$", "");
        }
        double value = Double.parseDouble(formattedNumber.replace(",", ""));
        return String.valueOf(value);

    }

    public static boolean isEmpty(List<?> list) {
        return !isNotEmpty(list);
    }

    public static boolean isNotEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static Object cloneObject(Object o) {
        String s = toJson(o);
        return toObject(s, o);
    }

    public static double parseDouble(String str) {
        try {
            if (notEmpty(str)) {
                str = str.replaceAll("[$,%]", "");
                return Double.parseDouble(str);
            } else {
                return 0;
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double parsePercentageAsDouble(String str) {
        return parseDouble(str) / 100.00d;
    }

    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String nullableDoubleToString(Double doubleValue, String nullSignifier) {
        if (doubleValue == null) {
            return nullSignifier;
        }
        if (Double.valueOf(0.0).equals(doubleValue)) {
            return nullSignifier;
        }
        return doubleValue.toString();
    }

    public static Double getAverage(List<Double> doubleList) {
        if (isEmpty(doubleList)) {
            return 0.0;
        }
        double sum = 0;
        for (Double d : doubleList) {
            sum += d;
        }
        return sum / doubleList.size();
    }

    public static Gson gson() {
        return new Gson();
    }

    public static void print(Object obj) {
        if (logger.isInfoEnabled()) {
            logger.info(gson().toJson(obj));
        }
    }

    public static String toJson(Object obj) {
        return gson().toJson(obj);
    }

    public static String encode64Bit(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static String decode64Bit(String dataIn64Bit) {
        byte[] decodedBytes = Base64.getDecoder().decode(dataIn64Bit);
        return Arrays.toString(decodedBytes);
    }

    public static String getAppBaseUrl(String contaxtPath) {
        UriComponents requestUri = ServletUriComponentsBuilder.fromCurrentRequestUri().build();
        return requestUri.getScheme() + "://" + requestUri.getHost()
                + (requestUri.getPort() > 0 ? (":" + requestUri.getPort()) : "") + contaxtPath;
    }

    public static Object toObject(String json, Object obj) {
        return gson().fromJson(json, obj.getClass());
    }

    public static boolean isEmptyJsonObject(Object obj) {
        return toJson(obj).equals("{}");
    }

    public static List<Object> getListFromIterator(Iterator<Object> iterator) {
        List<Object> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }

    public static Map getMapFromJson(String json) {
        return gson().fromJson(json, Map.class);
    }

    public static String getJsonFromMap(Map<String, Object> map) {
        return gson().toJson(map);
    }

    public static int getRandomNumber(int size) {
        Random r = new Random(System.currentTimeMillis());
        return (int) ((1 + r.nextInt(2)) * Math.pow(10, size - 1) + r.nextInt((int) Math.pow(10, size - 1)));
    }

    public static String generateId(String... ids) {
        int index = 0;
        StringBuilder returnId = new StringBuilder();
        while (index < ids.length) {
            if (index == 0) {
                returnId = new StringBuilder(ids[index].toLowerCase().trim());
            } else {
                returnId.append("@").append(ids[index].toLowerCase().trim());
            }
            index++;
        }
        return returnId.toString();

    }

    public static String getDocumentId() {
        return UUID.randomUUID().toString();
    }

    public static String removeRepeatedWords(String str) {
        return new LinkedHashSet<>(Arrays.asList(str.split("\\s+"))).toString().replaceAll("[\\[\\],]", "");

    }

    public static boolean numberNotExists(String string) {
        return string.matches(".*\\d.*");
    }

    public static <T> int nonEmptyMapCount(List<Map<String, T>> dataMapList) {
        int count = 0;
        for (Map<String, T> dataMap : dataMapList) {
            if (!dataMap.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public static String prettyPrintListOfStrings(List<String> inputList) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        inputList.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    public static String titleCase(String givenString) {
        if (Objects.equals(givenString, "")) return "";
        String[] arr = givenString.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }

    public static boolean isArrayContainElement(int[] Array, int element) {
        // code for ---if an element is present in an array
        for (int x : Array) {
            if (x == element)
                return true;
        }
        return false;
    }

    public static String getExtension(String fileUri) {
        return fileUri.substring(fileUri.lastIndexOf(".") + 1);
    }

    public static int compare(String s1, String s2) {
        int comparison = 0;
        int c1;
        int c2;
        int s1length = s1.length();
        int s2length = s2.length();
        for (int i = 0; i < s1length && i < s2length; i++) {
            c1 = s1.toLowerCase().charAt(i); // See note 1
            c2 = s2.toLowerCase().charAt(i); // See note 1
            comparison = c1 - c2; // See note 2

            if (comparison != 0) // See note 3
                return comparison;
        }
        return Integer.compare(s1length, s2length);

    }

    public static String stackTraceToString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public static String toUrlEncodeJson(Object obj) {
        return URLEncoder.encode(CodeHelp.toJson(obj), StandardCharsets.UTF_8);
    }

    public static String epochSecondsToDateString(long unixSeconds, String dateFormat) {
        Date date = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    public static boolean isValidStringPattern(String value, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(value).matches();
    }

    public static String padding(String value, int length, char symbol) {
        return Strings.padStart(value, length, symbol);
    }

    public static List<String> delimateStringColumns(List<String> columns) {
        List<String> list = Lists.newArrayList();
        for (String column : columns) {
            list.add("\"" + column + "\"");
        }
        return list;
    }

    public static String sanitizeName(String name) {
        // Only allow alphabets, single space and single quote in name
        return name.replaceAll("[^a-zA-Z ']", "");
    }

    public static Date mongoDateStringToDateObject(String mongoDateString) throws ParseException {
        return parseDateString(mongoDateString, DateTimeFormatEnum.FULL_DATE_TIME.toString());
    }

    public static Date parseISOmillisecondsDateString(String isoMsString) throws ParseException {
        return parseDateString(isoMsString, DateTimeFormatEnum.FULL_DATE_TIME_ISO_MS.toString());
    }

    public static Date parseDateString(String dateString, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.parse(dateString);
    }

    public static String getISODateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFormatEnum.FULL_DATE_TIME_ISO_MS.toString());
        return sdf.format(date);
    }

    public static Date getEndOfTheDayTime(String date) {
        Date toDate = null;
        try {
            toDate = CodeHelp.parseISOmillisecondsDateString(date);
        } catch (ParseException e) {
            toDate = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        toDate = calendar.getTime();
        return toDate;
    }

    public static Double getNormalizedGpa(Double gpaScore, Double gpaScale) {
        if (gpaScore == null) {
            return null;
        }
        if (gpaScale == null) {
            gpaScale = 4.00;
        }
        if (gpaScale.equals(0.0)) {
            gpaScale = 4.00;
        }

        return gpaScore * 4.0 / gpaScale;
    }

    public static Boolean parseResponseAsBoolean(String responseText) {
        if (isEmpty(responseText)) {
            return null;
        }
        List<String> truthyValues = Arrays.asList("y", "yes", "true", "ok", "1", "Accepted");
        List<String> falsyValues = Arrays.asList("n", "no", "false", "0", "Denied");

        responseText = responseText.toLowerCase();
        if (truthyValues.contains(responseText)) {
            return true;
        }
        if (falsyValues.contains(responseText)) {
            return false;
        }
        return null;
    }

    public static String listOfMapToCsv(List<Map<String, String>> mergedDataMap) {
        List<String> rows = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        for (Map<String, String> dataMap : mergedDataMap) {
            for (Entry<String, String> dataMapElem : dataMap.entrySet()) {
                if (headers.contains(dataMapElem.getKey())) {
                    // skip duplicate addition
                } else {
                    headers.add(dataMapElem.getKey());
                }
            }
        }
        // rows.add(String.join(",", CodeHelp.delimateStringColumns(headers)));
        rows.add(String.join(",", CodeHelp.delimateStringColumns(headers)));
        for (Map<String, String> dataMap : mergedDataMap) {
            List<String> row = new ArrayList<>(headers.size());
            for (int i = 0; i < headers.size(); i++) {
                row.add(i, " ");
            }
            for (Entry<String, String> dataMapElem : dataMap.entrySet()) {
                int index = headers.indexOf(dataMapElem.getKey());
                row.set(index, dataMapElem.getValue());
            }
            if (!row.isEmpty()) {
                // rows.add(String.join(",", CodeHelp.delimateStringColumns(row)));
                rows.add(String.join(",", CodeHelp.delimateStringColumns(row)));

            }
        }
        String requiredCSVFormat = String.join("\n", rows);
        requiredCSVFormat = requiredCSVFormat.replace("[", "");
        requiredCSVFormat = requiredCSVFormat.replace("]", "");
        return requiredCSVFormat;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seenAlready = ConcurrentHashMap.newKeySet();
        return t -> seenAlready.add(keyExtractor.apply(t));
    }

    public static boolean validateDateFormat(String date, String dateFormat) {
        try {
            new SimpleDateFormat(dateFormat).parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static double roundToNearest100(double exactAmount) {
        int remainder = (int) (exactAmount % 100);
        int quotient = (int) (exactAmount / 100);

        if (remainder < 50) {
            return quotient * 100;
        } else {
            return (quotient + 1) * 100;
        }
    }

    public static double roundUpToNearest100(double exactAmount) {
        int remainder = (int) (exactAmount % 100);
        int quotient = (int) (exactAmount / 100);
        if (remainder > 0) {
            return (quotient + 1) * 100.0;
        }
        return quotient * 100.0;
    }

    public static void filesToZip(HttpServletResponse response, List<File> files, String zipFileName)
            throws IOException {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s", zipFileName) + ".zip");
        // Create a buffer for reading the files
        byte[] buf = new byte[1024];
        // create the ZIP file
        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        // compress the files
        for (File file : files) {
            try (FileInputStream in = new FileInputStream(file.getName())) {
                // add ZIP entry to output stream
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                // transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    zipOutputStream.write(buf, 0, len);
                }
                // complete the entry
                zipOutputStream.closeEntry();
            }
        }
        // complete the ZIP file
        zipOutputStream.close();
    }

    /**
     * Replaces &nbsp; by normal whitespaces then trims it
     **/
    public static String trimWhitespaces(String inputString) {
        if (!StringUtils.hasLength(inputString)) {
            return inputString;
        }
        return inputString.replace("\u00a0", " ").trim();
    }

    public static boolean sanitizeString(String string) {
        return true;
    }

    public static double sanitizeAsDouble(String valueToBeSanitized) {
        double sanitizedValue = 0;
        try {
            sanitizedValue = Double.parseDouble(valueToBeSanitized);
        } catch (Exception e) {
            logger.error("Error while sanitizing value as double", e);
        }
        return sanitizedValue;
    }

    public static Map<String, String> sanitizeDataMap(Map<String, String> dataMap) {
        Map<String, String> sanitizedData = new HashMap<>();
        for (Entry<String, String> elem : dataMap.entrySet()) {
            if (elem.getValue().compareTo(" ") == 0) {
                elem.setValue("");
            }
            if (elem.getValue().matches("^(\\d{1,3}(,\\d{3})*|(\\d+))(\\.\\d{2})?$")) {
                elem.setValue(elem.getValue().replace(",", ""));
            }
            if (elem.getValue().compareTo("NULL") == 0 || elem.getValue().compareTo("Null") == 0
                    || elem.getValue().compareTo("null") == 0) {
                elem.setValue("");
            }
            sanitizedData.put(elem.getKey(), (elem.getValue().compareTo(" ") == 0 ? "" : elem.getValue()));
        }
        return sanitizedData;
    }

    public static String usCurrencyFormat(String value) {
        Locale usa = new Locale("en", "US");
        // Create a formatter given the Locale
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
        dollarFormat.setMaximumFractionDigits(0);
        return dollarFormat.format(Double.valueOf(value));
    }

    public static String filterNumber(String value) {
        return CharMatcher.inRange('0', '9').retainFrom(value);
    }

}
