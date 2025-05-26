/******************************************************************
 *
 * @copyright 2022 DotEvolve
 * @author
 * @since
 ******************************************************************/
package net.dotevolve.base.structure;

import net.dotevolve.base.data.BaseEntity;

public interface ICollectionInfo<Data extends BaseEntity> {
    String getName();

    Data getType(String jsonString);
}
