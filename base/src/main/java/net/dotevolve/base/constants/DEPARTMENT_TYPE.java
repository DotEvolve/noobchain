/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 15/02/23, 9:45 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.constants;

import lombok.Getter;

@Getter
public enum DEPARTMENT_TYPE {
    UNKNOWN("0", "Unknown");

    private final String id;
    private final String department;

    DEPARTMENT_TYPE(String id, String department) {
        this.id = id;
        this.department = department;
    }

}
