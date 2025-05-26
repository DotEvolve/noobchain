/******************************************************************
 *
 * @copyright 2022 DotEvolve
 * @author
 * @since
 ******************************************************************/
package net.dotevolve.base.service;

import net.dotevolve.base.data.BaseEntity;

public abstract class ASaveKeeper<Data extends BaseEntity> {

    public abstract Data update(Data oldData, Data newData);

}
