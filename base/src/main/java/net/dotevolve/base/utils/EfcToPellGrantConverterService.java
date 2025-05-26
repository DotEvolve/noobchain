/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import org.bson.Document;

@Component
public class EfcToPellGrantConverterService {
    @Autowired
    @Qualifier("thirdPartyTablesMongoTemplate")
    MongoTemplate mongoTemplate;

    public double convertToPellGrant2022(double efc, String studentEnrollmentStatus) {
        double pellGrantValue = 0d;
        int coa = 6895;
        Query query = new Query();
        String field = null;
        query.addCriteria(new Criteria().andOperator(Criteria.where("min").lte(coa), Criteria.where("max").gte(coa),
                Criteria.where("payment_schedule").is(studentEnrollmentStatus),
                Criteria.where("year").is("2022-2023")));
        Document doc = Optional.ofNullable(mongoTemplate.findOne(query, Document.class, "pell_grant"))
                .orElse(new Document());
        for (String key : doc.keySet()) {
            if (key.contains("_") && !key.equals("payment_schedule") && !key.equals("_id")) {
                String[] spl = key.split("_");
                int min1 = Integer.parseInt(spl[0]);
                int max1 = Integer.parseInt(spl[1]);
                if (min1 <= efc && max1 >= efc) {
                    field = key;
                }
            }
        }
        if (field != null) {
            pellGrantValue = Double.parseDouble(doc.get(field).toString());
        }
        return pellGrantValue;
    }

    public double convertToPellGrant2021(double efc, String studentEnrollmentStatus) {
        double pellGrantValue = 0d;
        int coa = 6495;
        Query query = new Query();
        String field = null;
        query.addCriteria(new Criteria().andOperator(Criteria.where("min").lte(coa), Criteria.where("max").gte(coa),
                Criteria.where("payment_schedule").is(studentEnrollmentStatus),
                Criteria.where("year").is("2021-2022")));
        Document doc = Optional.ofNullable(mongoTemplate.findOne(query, Document.class, "pell_grant"))
                .orElse(new Document());
        for (String key : doc.keySet()) {
            if (key.contains("_") && !key.equals("payment_schedule") && !key.equals("_id")) {
                String[] spl = key.split("_");
                int min1 = Integer.parseInt(spl[0]);
                int max1 = Integer.parseInt(spl[1]);
                if (min1 <= efc && max1 >= efc) {
                    field = key;
                }
            }
        }
        if (field != null) {
            pellGrantValue = Double.parseDouble(doc.get(field).toString());
        }
        return pellGrantValue;
    }

}
