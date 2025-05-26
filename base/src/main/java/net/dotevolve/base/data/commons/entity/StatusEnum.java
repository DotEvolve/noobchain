/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 10/02/23, 2:08 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.data.commons.entity;

import lombok.Getter;

@Getter
public enum StatusEnum {
    PENDING("1", "Pending"),
    INITIATED("2", "Initiated"),
    IN_PROGRESS("3", "In Progress"),
    COMPLETED("4", "Completed");

    private final String id;
    private final String status;

    StatusEnum(String id, String status) {
        this.id = id;
        this.status = status;
    }

}
