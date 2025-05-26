/******************************************************************
 *
 * @copyright 2022 DotEvolve
 * @author
 * @since
 ******************************************************************/
package net.dotevolve.base.utils;

public class CodeCondition {

    public static void validate(boolean condition, String showError, Object... objects) {
        if (!condition) {
            throw new RuntimeException(showError);
        }
    }
}
