/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.service;

import java.util.List;

import net.dotevolve.base.data.BaseEntity;

import org.apache.commons.compress.utils.Lists;

public abstract class AChangeHandler<Data extends BaseEntity> {

    private final List<AChangeHandler<Data>> additionalChangeHandlers = Lists.newArrayList();

    public abstract void onChange(Data data);

    public void addChangeHandler(AChangeHandler<Data> data) {
        additionalChangeHandlers.add(data);
    }

    public List<AChangeHandler<Data>> additionalChangeHandlers() {
        return additionalChangeHandlers;
    }

}
