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
import net.dotevolve.base.structure.IDatabaseStore;
import net.dotevolve.base.utils.DateTimeFormatEnum;

import com.mongodb.client.result.InsertManyResult;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class CreateService<Data extends BaseEntity> {

    private final ASaveKeeper<Data> saveKeeper;
    private final AChangeHandler<Data> handler;
    private final IDatabaseStore<Data> store;

    public CreateService(ASaveKeeper<Data> saveKeeper, AChangeHandler<Data> handler, IDatabaseStore<Data> store) {
        this.handler = handler;
        this.store = store;
        this.saveKeeper = saveKeeper;
    }

    public Data create(Data data) {
        Data dataCreated = store.create(saveKeeper.update(null, data));
        try {
            handler.onChange(dataCreated);
        } catch (Exception e) {
            // Handle exception
        }
        for (AChangeHandler<Data> changeHandler : handler.additionalChangeHandlers()) {
            try {
                changeHandler.onChange(dataCreated);
            } catch (Exception e) {
                // Handle exception
            }
        }
        return dataCreated;
    }

    public InsertManyResult insertMany(List<Data> dataList) {

        InsertManyResult result;
        if (dataList != null && !dataList.isEmpty()) {
            dataList.forEach(x -> x.getMetaData().setCreatedAt(getCurrentDateTime()));
            result = store.insertMany(dataList);
        } else {
            result = InsertManyResult.unacknowledged();
        }
        return result;
    }

    public String getCurrentDateTime() {
        DateTime time = new DateTime();
        return time.toString(DateTimeFormat.forPattern(DateTimeFormatEnum.FULL_DATE_TIME.toString()));
    }
}
