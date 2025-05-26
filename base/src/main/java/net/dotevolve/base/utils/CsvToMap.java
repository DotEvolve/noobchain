/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvToMap {

    private CsvToMap() {
        // Prevent instantiation
    }

    public static List<Map<String, String>> read(ByteArrayResource src) throws IOException {
        List<Map<String, String>> response = new ArrayList<>();
        CsvMapper mapper = new CsvMapper();
        mapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = mapper.readerFor(Map.class).with(schema)
                .readValues(src.getInputStream());
        while (iterator.hasNext()) {
            response.add(iterator.next());
        }
        return response;
    }
}
