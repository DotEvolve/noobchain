/******************************************************************
 *
 * @copyright 2022 DotEvolve
 * @author
 * @since
 ******************************************************************/
package net.dotevolve.base.data;

import lombok.Data;

@Data
public class MetaDataEntity {

    private String createdAt;
    private String updatedAt;
    private int version = 0;
    private LifeTimeStateEnum state = LifeTimeStateEnum.ACTIVE;

}
