/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.dotevolve.base.data.LifeTimeStateEnum;
import net.dotevolve.base.data.MetaDataEntity;
import net.dotevolve.base.utils.DateTimeFormatEnum;
import net.dotevolve.base.utils.DateUtil;

@Component
public class EntityMetadataUpdater {

    @Autowired
    DateUtil dateUtil;

    public void updateMetadata(MetaDataEntity metaDataEntity, boolean isNew) {
        metaDataEntity.setState(LifeTimeStateEnum.ACTIVE);
//		metaDataEntity.setVersion(metaDataEntity.getVersion() + 1);
        if (!isNew) {
            metaDataEntity.setUpdatedAt(dateUtil.getCurrentDateTime(DateTimeFormatEnum.FULL_DATE_TIME));
        } else {
            metaDataEntity.setCreatedAt(dateUtil.getCurrentDateTime(DateTimeFormatEnum.FULL_DATE_TIME));
        }

    }
}
