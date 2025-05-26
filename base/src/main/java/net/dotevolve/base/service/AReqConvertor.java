/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.service;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.util.Pair;

import net.dotevolve.base.data.BaseEntityReq;
import net.dotevolve.base.data.PaginationEntity;
import net.dotevolve.base.structure.IConvertor;
import net.dotevolve.base.utils.CodeCondition;

public abstract class AReqConvertor<SReq extends BaseEntityReq> implements IConvertor<SReq, Query> {

    public abstract Query convert(SReq request);

    public Pair<Query, PaginationEntity> convertWithPagination(SReq request) {
        CodeCondition.validate(false, "using convertWithPagination but not implemented", request);
        return null;
    }
}
