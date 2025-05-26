/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 07/03/23, 1:57 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import org.springframework.stereotype.Component;

@Component
public class DefaultFieldIdProvider {

    // Generic field IDs
    public static final int ID = 10;

    // User field IDs - 100
    public static final int NAME = 100;
    public static final int FIRST_NAME = 110;
    public static final int LAST_NAME = 120;
    public static final int GENDER = 140;

    // Contact field IDs - 200
    public static final int CONTACT = 200;
    public static final int EMAIL = 210;
    public static final int MOBILE = 250;
    public static final int PHONE = 260;

    // Address field IDs - 300
    public static final int ADDRESS = 300;
    public static final int COUNTRY = 310;
    public static final int STATE = 320;
    public static final int CITY = 330;
    public static final int ZIPCODE = 340;
    public static final int LOCALITY = 350;
    public static final int LANDMARK = 360;
    public static final int AREA = 370;
    public static final int STREET = 380;
    public static final int HOUSE_NUMBER = 390;

    // Organization field IDs - 400
    public static final int ORGANIZATION = 400;
    public static final int ORGANIZATION_NAME = 410;
    public static final int ORGANIZATION_TYPE = 420;
    public static final int ORGANIZATION_DESCRIPTION = 430;
    public static final int DEPARTMENT = 440;
    public static final int DEPARTMENT_NAME = 450;
    public static final int DEPARTMENT_DESCRIPTION = 460;

    // Payment field IDs - 500
    public static final int PAYMENT = 500;
    public static final int PAYMENT_TYPE = 510;
    public static final int PAYMENT_AMOUNT = 520;
    public static final int PAYMENT_DATE = 530;
    public static final int PAYMENT_STATUS = 540;
    public static final int PAYMENT_DESCRIPTION = 550;
    public static final int PAYMENT_GATEWAY = 560;
    public static final int PAYMENT_GATEWAY_NAME = 570;
    public static final int PAYMENT_GATEWAY_DESCRIPTION = 580;
    public static final int PAYMENT_GATEWAY_RESPONSE = 590;

    // Transaction field IDs - 500
    public static final int TRANSACTION = 600;
    public static final int TRANSACTION_TYPE = 610;
    public static final int TRANSACTION_AMOUNT = 620;
    public static final int TRANSACTION_DATE = 630;
    public static final int TRANSACTION_STATUS = 640;
    public static final int TRANSACTION_DESCRIPTION = 650;

    private DefaultFieldIdProvider() {
    }
}
