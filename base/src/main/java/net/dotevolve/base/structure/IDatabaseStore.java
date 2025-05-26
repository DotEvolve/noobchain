/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.structure;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.util.Pair;

import net.dotevolve.base.data.BaseEntity;
import net.dotevolve.base.data.BaseEntityResp;
import net.dotevolve.base.data.PaginationEntity;
import net.dotevolve.base.data.SearchCountResp;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;

public interface IDatabaseStore<Data extends BaseEntity> {

    Data create(Data data);

    SearchCountResp count(Query request);

    Data update(Data data, String id);

    Data get(String id);

    Pair<List<Data>, PaginationEntity> search(Query req);

    BaseEntityResp searchWithPagination(Pair<Query, PaginationEntity> queryPairWithPagination);

    boolean cacheEnable();

    BulkWriteResult updateMany(List<Data> dataList);

    InsertManyResult insertMany(List<Data> dataList);

    BulkWriteResult updateMany(List<Data> dataList, boolean upsert);

    BulkWriteResult upsertMany(List<Data> dataList);

    DeleteResult deleteMany(List<Data> data);

    Pair<List<Data>, PaginationEntity> findAndProject(Query req);
}
