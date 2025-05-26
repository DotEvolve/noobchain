/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.dotevolve.base.data.BaseEntity;
import net.dotevolve.base.structure.IDatabaseStore;

@Service
public class GetService<Data extends BaseEntity> {

    IDatabaseStore<Data> store;

    @Autowired
    public GetService(IDatabaseStore<Data> store) {
        this.store = store;
    }

    public Data get(String id) {
        return store.get(id);
    }
}
