/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 02/02/23, 2:29 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.constants;

import static net.dotevolve.base.constants.CONFIG_ID.ListNames.ARTICLES_LIST_NAME;

import lombok.Getter;

@Getter
public enum CONFIG_ID {
    ARTICLES_LIST(ARTICLES_LIST_NAME);

    private final String value;

    CONFIG_ID(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CONFIG_ID{" +
                "value='" + value + '\'' +
                '}';
    }

    enum ListNames {
        ;
        static final String ARTICLES_LIST_NAME = "articlesList";
    }
}