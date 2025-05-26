/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 10/02/23, 1:49 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.data.commons.entity;

import net.dotevolve.base.constants.DEPARTMENT_TYPE;
import net.dotevolve.base.data.DataRef;

import lombok.Data;

@Data
public class TaskDetailEntity {
    private String articleId;
    private String materialId;
    private String colorId;
    private String soleId;
    private String size06;
    private String size07;
    private String size08;
    private String size09;
    private String size10;
    private String size11;
    private String size12;
    private StatusEnum status;
    private DEPARTMENT_TYPE departmentTypeEnum;
    private DataRef assignedTo;
    private String formFilledId;
    private double bottomRate;
    private double cuttingRate;
    private double upperRate;
    private double polishRate;
    private double finishRate;
    private double packagingRate;

}
