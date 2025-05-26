/******************************************************************
 *
 * @copyright 2022 DotEvolve
 * @author
 * @since
 ******************************************************************/
package net.dotevolve.base.service;

import net.dotevolve.base.data.BaseEntity;
import net.dotevolve.base.data.BaseEntityReq;
import net.dotevolve.base.data.SearchCountResp;
import net.dotevolve.base.structure.IDatabaseStore;

public class CountService<Data extends BaseEntity, SReq extends BaseEntityReq> {

    IDatabaseStore<Data> store;

    AReqConvertor<SReq> converter;

    public CountService(IDatabaseStore<Data> store, AReqConvertor<SReq> converter) {
        this.store = store;
        this.converter = converter;
    }

    public SearchCountResp count(SReq request) {
        return store.count(converter.convert(request));
    }
}
