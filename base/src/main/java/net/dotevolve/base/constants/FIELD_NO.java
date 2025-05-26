/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 09/02/23, 2:08 pm
 *
 *
 ******************************************************************************/

package net.dotevolve.base.constants;

import lombok.Getter;

@Getter
public enum FIELD_NO {

    ID(10, "id"),
    FIRST_NAME(100, "first_name"),
    LAST_NAME(110, "last_name");

    public static final int GENDER = 120;

    // Contact field IDs - 200
    public static final int EMAIL = 200;
    public static final int MOBILE = 250;
    public static final int PHONE = 260;

    // Address field IDs - 300
    public static final int ADDRESS = 300;
    public static final int COUNTRY = 310;
    public static final int STATE = 320;
    public static final int CITY = 330;
    public static final int POST_OFFICE = 340;
    public static final int LOCALITY = 350;
    public static final int LANDMARK = 360;
    public static final int ADDRESS_LINE_1 = 370;
    public static final int BUILDING_NAME = 380;
    public static final int FLAT_NO = 390;

    // Mascot Footwear field IDs - 10
    // workers - 10
    public static final int WORKER = 101010;
    public static final int WORKER_ID = 101011;
    public static final int UPPER_MAN = 101020;
    public static final int CUTTING_MAN = 101030;
    public static final int BOTTOM_MAN = 101040;
    public static final int POLISH_MAN = 101050;

    // customer - 20
    public static final int CUSTOMER = 102010;
    public static final int CUSTOMER_ID = 102011;

    // task details - 50
    public static final int TASK_DETAILS = 105010;

    // product - 30
    public static final int ARTICLE = 103010;
    public static final int ARTICLE_ID = 103011;
    public static final int ARTICLE_NAME = 103012;
    public static final int ARTICLE_DESCRIPTION = 103013;
    public static final int ARTICLE_CUTTING_RATE = 103014;
    public static final int ARTICLE_BOTTOM_RATE = 103015;
    public static final int ARTICLE_UPPER_RATE = 103016;
    public static final int ARTICLE_POLISH_RATE = 103017;
    public static final int COLOR = 103020;
    public static final int COLOR_ID = 103021;
    public static final int COLOR_NAME = 103022;
    public static final int COLOR_HEX_CODE = 103023;
    public static final int MATERIAL = 103030;
    public static final int MATERIAL_ID = 103031;
    public static final int MATERIAL_NAME = 103032;
    public static final int MATERIAL_TYPE = 103033;
    public static final int SOLE = 103040;
    public static final int SOLE_ID = 103041;
    public static final int SOLE_NAME = 103042;
    public static final int SOLE_TYPE = 103043;

    // sizes - 40
    public static final int SIZE_06 = 104006;
    public static final int SIZE_07 = 104007;
    public static final int SIZE_08 = 104008;
    public static final int SIZE_09 = 104009;
    public static final int SIZE_10 = 104010;
    public static final int SIZE_11 = 104011;
    public static final int SIZE_12 = 104012;

    private final int id;
    private final String value;

    FIELD_NO(int id, String value) {
        this.id = id;
        this.value = value;
    }

}
