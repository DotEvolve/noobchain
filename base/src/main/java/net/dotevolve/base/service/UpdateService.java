/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/
package net.dotevolve.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.dotevolve.base.data.BaseEntity;
import net.dotevolve.base.data.MetaDataEntity;
import net.dotevolve.base.structure.IDatabaseStore;
import net.dotevolve.base.utils.CodeCondition;
import net.dotevolve.base.utils.CodeHelp;
import net.dotevolve.base.utils.DateTimeFormatEnum;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.DeleteResult;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

@Service
public class UpdateService<Data extends BaseEntity> {

    private final ASaveKeeper<Data> saveKeeper;
    private final AChangeHandler<Data> handler;
    private final IDatabaseStore<Data> store;

    @Autowired
    public UpdateService(ASaveKeeper<Data> saveKeeper, AChangeHandler<Data> handler, IDatabaseStore<Data> store) {
        this.store = store;
        this.saveKeeper = saveKeeper;
        this.handler = handler;
    }

    public Data update(Data data, String id) {
        Data oldData = store.get(id);
        CodeCondition.validate(oldData.getMetaData().getVersion() == data.getMetaData().getVersion(),
                "Version not matched", data, oldData);
        data.getMetaData().setVersion(data.getMetaData().getVersion() + 1);
        saveKeeper.update(oldData, data);
        Data updatedData = store.update(data, id);
        try {
            handler.onChange(updatedData);
        } catch (Exception e) {
            // Handle exception
        }
        for (AChangeHandler<Data> changeHandler : handler.additionalChangeHandlers()) {
            try {
                changeHandler.onChange(updatedData);
            } catch (Exception e) {
                // Handle exception
            }
        }
        return updatedData;
    }

    public BulkWriteResult updateMany(List<Data> dataList, boolean upsert) {
        if (CodeHelp.isEmpty(dataList)) {
            return BulkWriteResult.unacknowledged();
        }
        dataList.forEach(this::updateMetaData);
        return store.updateMany(dataList, upsert);
    }

    private void updateMetaData(Data data) {
        if (data.getMetaData() == null) {
            data.setMetaData(new MetaDataEntity());
        }
        if (data.getMetaData().getCreatedAt() == null) {
            data.getMetaData().setCreatedAt(getCurrentDateTime());
        } else {
            data.getMetaData().setUpdatedAt(getCurrentDateTime());
        }
    }

    private String getCurrentDateTime() {
        DateTime time = new DateTime();
        return time.toString(DateTimeFormat.forPattern(DateTimeFormatEnum.FULL_DATE_TIME.toString()));
    }

    public BulkWriteResult updateMany(List<Data> dataList) {
        boolean upsert = false;
        return updateMany(dataList, upsert);
    }

    public DeleteResult deleteMany(List<Data> data) {
        return store.deleteMany(data);
    }

}
