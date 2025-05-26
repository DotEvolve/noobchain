/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import net.dotevolve.base.data.BaseEntity;
import net.dotevolve.base.data.BaseEntityResp;
import net.dotevolve.base.data.NextPagination;
import net.dotevolve.base.data.PaginationEntity;
import net.dotevolve.base.data.SearchCountResp;
import net.dotevolve.base.structure.ICollectionInfo;
import net.dotevolve.base.structure.IDatabaseStore;
import net.dotevolve.base.utils.CacheService;
import net.dotevolve.base.utils.CodeCondition;
import net.dotevolve.base.utils.CodeHelp;

import com.mongodb.BasicDBObject;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import org.bson.Document;

@Component
public abstract class AMongoDatabaseStore<Data extends BaseEntity, CollectionInfo extends ICollectionInfo<Data>>
        implements IDatabaseStore<Data> {
    @Autowired
    CollectionInfo collectionInfo;

    @Autowired
    @Qualifier("primaryMongoTemplate")
    MongoTemplate primaryMongoTemplate;

    @Autowired
    CacheService<Data> cacheService;

    @Override
    public boolean cacheEnable() {
        return false;
    }

    @Override
    public Data create(Data data) {
        if (CodeHelp.isEmpty(data.getId())) {
            data.setId(CodeHelp.getDocumentId());
        }
        primaryMongoTemplate.insert(data, collectionInfo.getName());
        return data;
    }

    @Override
    public Data get(String id) {
        if (cacheEnable() && cacheService.exists(id, collectionInfo.getName())) {
            return cacheService.get(id, collectionInfo.getName()).get();
        }
        BasicDBObject data = primaryMongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)),
                BasicDBObject.class, collectionInfo.getName());
        CodeCondition.validate(data != null, "Data not available on id = " + id, id);
        if (cacheEnable()) {
            return cacheService.save(id, collectionInfo.getType(data.toJson()), collectionInfo.getName());
        }
        return collectionInfo.getType(data.toJson());
    }

    @Override
    public Data update(Data data, String id) {
        primaryMongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(id)),
                Update.fromDocument(Document.parse(CodeHelp.toJson(data)), ""), BasicDBObject.class,
                collectionInfo.getName());
        if (cacheEnable()) {
            cacheService.save(id, data, collectionInfo.getName());
        }
        return data;
    }
//value	"eb180589-d1de-4edc-abbf-e6f87c38af43" (id=256)	

    @Override
    public Pair<List<Data>, PaginationEntity> search(Query req) {
        List<BasicDBObject> dbObjects = primaryMongoTemplate.find(req, BasicDBObject.class, collectionInfo.getName());
        List<Data> resp = new ArrayList<>();
        for (BasicDBObject dbObject : dbObjects) {
            resp.add(collectionInfo.getType(dbObject.toJson()));
        }
        PaginationEntity pagination = new PaginationEntity();
        pagination.setTotalSize(resp.size());
        return Pair.of(resp, pagination);
    }

    @Override
    public SearchCountResp count(Query req) {
        long totalCount = primaryMongoTemplate.count(req, BasicDBObject.class, collectionInfo.getName());
        SearchCountResp response = new SearchCountResp();
        response.setTotalCount(totalCount);
        return response;
    }

    @Override
    public BaseEntityResp searchWithPagination(Pair<Query, PaginationEntity> queryPairWithPagination) {
        long size = primaryMongoTemplate.count(queryPairWithPagination.getFirst(), BasicDBObject.class,
                collectionInfo.getName());
        Pageable pageable = null;
        if (queryPairWithPagination.getSecond().getTotalSize() < size) {
            pageable = PageRequest.of((int) queryPairWithPagination.getSecond().getPageNo() - 1,
                    (int) queryPairWithPagination.getSecond().getTotalSize());
            queryPairWithPagination.getFirst().with(pageable);
        }
        List<BasicDBObject> dbObjects = primaryMongoTemplate.find(queryPairWithPagination.getFirst(),
                BasicDBObject.class, collectionInfo.getName());
        List<Data> resp = new ArrayList<>();
        for (BasicDBObject dbObject : dbObjects) {
            resp.add(collectionInfo.getType(dbObject.toJson()));
        }
        BaseEntityResp baseResp = new BaseEntityResp();
        baseResp.setResponse(resp);
        baseResp.setNextPage(new NextPagination());
        if (pageable != null) {
            Page<BasicDBObject> pu = new PageImpl<>(dbObjects, pageable, size);
            if ((long) (pu.getPageable().getPageNumber() + 1) * pu.getPageable().getPageSize() < size) {
                baseResp.getNextPage().setNextPageNumber(queryPairWithPagination.getSecond().getPageNo() + 1);
            } else {
                baseResp.getNextPage().setNextPageNumber(0);
            }
        } else {
            baseResp.getNextPage().setNextPageNumber(0);
        }
        baseResp.getNextPage().setTotalHits(size);
        return baseResp;
    }

    @Override
    public InsertManyResult insertMany(List<Data> dataList) {
        List<Document> documents = new ArrayList<>();
        for (Data data : dataList) {
            Document document = Document.parse(CodeHelp.toJson(data));
            documents.add(document);
        }

        return primaryMongoTemplate.getCollection(collectionInfo.getName())
                .insertMany(documents);
    }

    @Override
    public BulkWriteResult updateMany(List<Data> dataList) {
        return updateMany(dataList, false);
    }

    @Override
    public BulkWriteResult updateMany(List<Data> dataList, boolean upsert) {
        MongoCollection<Document> collection = primaryMongoTemplate.getCollection(collectionInfo.getName());
        List<WriteModel<Document>> writes = new ArrayList<>();
        ReplaceOptions updateOptions = new ReplaceOptions();
        updateOptions.upsert(upsert);

        for (Data data : dataList) {
            WriteModel<Document> writeModel;
            if (CodeHelp.isEmpty(data.getId())) {
                data.setId(CodeHelp.getDocumentId());
                writeModel = new InsertOneModel<>(Document.parse(CodeHelp.toJson(data)));
            } else {
                Query filter = Query.query(Criteria.where("_id").is(data.getId()));
                Document replacement = Document.parse(CodeHelp.toJson(data));
                writeModel = new ReplaceOneModel<>(filter.getQueryObject(), replacement, updateOptions);
            }
            writes.add(writeModel);
        }

        return collection.bulkWrite(writes);

    }

    @Override
    public BulkWriteResult upsertMany(List<Data> dataList) {
        MongoCollection<Document> collection = primaryMongoTemplate.getCollection(collectionInfo.getName());
        List<WriteModel<Document>> writes = new ArrayList<>();
        for (Data data : dataList) {
            Document filter = Document.parse(CodeHelp.toJson(data));
            Query query = Query.query(Criteria.matchingDocumentStructure(MongoJsonSchema.of(filter)));
            Document replacement = Document.parse(CodeHelp.toJson(data));
            ReplaceOptions updateOptions = new ReplaceOptions().upsert(true);
            ReplaceOneModel<Document> writeModel = new ReplaceOneModel<>(query.getQueryObject(), replacement,
                    updateOptions);
            writes.add(writeModel);
        }
        return collection.bulkWrite(writes);
    }

    @Override
    public DeleteResult deleteMany(List<Data> dataList) {
        MongoCollection<Document> collection = primaryMongoTemplate.getCollection(collectionInfo.getName());
        List<String> ids = dataList.stream().map(BaseEntity::getId).collect(Collectors.toList());
        return collection.deleteMany(Filters.in("_id", ids));
    }

    @Override
    public Pair<List<Data>, PaginationEntity> findAndProject(Query req) {
        List<BasicDBObject> dbObjects = primaryMongoTemplate.find(req, BasicDBObject.class, collectionInfo.getName());
        List<Data> resp = new ArrayList<>();
        for (BasicDBObject dbObject : dbObjects) {
            resp.add(collectionInfo.getType(dbObject.toJson()));
        }
        PaginationEntity pagination = new PaginationEntity();
        pagination.setTotalSize(resp.size());
        return Pair.of(resp, pagination);
    }

}
