/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 02/02/23, 2:01 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.constants;

import lombok.Getter;

@Getter
public enum FIELD_TYPE {
    TEXT("text"),
    NUMBER("number"),
    DATE("date"),
    TIME("time"),
    DATETIME("datetime"),
    EMAIL("email"),
    PASSWORD("password"),
    CHECKBOX("checkbox"),
    RADIO("radio"),
    SELECT("select"),
    TEXTAREA("textarea"),
    FILE("file"),
    IMAGE("image"),
    COLOR("color"),
    TEL("tel"),
    URL("url"),
    RANGE("range"),
    SEARCH("search"),
    MONTH("month"),
    WEEK("week"),
    HIDDEN("hidden");

    private final String value;

    FIELD_TYPE(String value) {
        this.value = value;
    }

}