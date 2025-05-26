package net.dotevolve.noobchain.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import net.dotevolve.noobchain.exception.SHA256Exception;

import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {

    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    private StringUtil() {
        // Private constructor to prevent instantiation
    }

    //Applies Sha256 to a string and returns the result.
    public static String applySha256(String input) throws SHA256Exception {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            //Applies sha256 to our input,
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(); // This will contain hash as hexadecimal
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            logger.error("Error applying SHA-256 to input: {}", input, e);
            throw new SHA256Exception("SHA-256 hashing failed for input: " + input);
        }
    }

    //Shorthand helper to turn Object into a json string
    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //Returns difficulty string target, to compare to hash. e.g. difficulty of 5 will return "00000"
    public static String getDificultyString(int difficulty) {
        return String.valueOf(new char[difficulty]).replace('\0', '0');
    }

}
