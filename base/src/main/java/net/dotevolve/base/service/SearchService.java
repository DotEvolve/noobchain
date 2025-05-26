/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.service;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import net.dotevolve.base.data.BaseEntity;
import net.dotevolve.base.data.BaseEntityReq;
import net.dotevolve.base.data.BaseEntityResp;
import net.dotevolve.base.structure.IDatabaseStore;


public class SearchService<Data extends BaseEntity, SReq extends BaseEntityReq> {

    IDatabaseStore<Data> store;

    AReqConvertor<SReq> converter;

    public SearchService(IDatabaseStore<Data> store, AReqConvertor<SReq> converter) {
        this.store = store;
        this.converter = converter;
    }

    public List<Data> search(SReq request) {
        return store.search(converter.convert(request)).getFirst();
    }

    public BaseEntityResp searchPage(SReq request) {
        return store.searchWithPagination(converter.convertWithPagination(request));
    }

    public List<Data> find(Query query) {
        return store.findAndProject(query).getFirst();
    }
}
