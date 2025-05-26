/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CsvToMapTest {

    @Test
    public void testReadValidCsv() throws IOException {
        // Prepare test data
        String csvContent = """
                name,age,city
                John,30,New York
                Jane,25,San Francisco
                Bob,40,Chicago""";

        ByteArrayResource resource = new ByteArrayResource(csvContent.getBytes(StandardCharsets.UTF_8));

        // Execute the method
        List<Map<String, String>> result = CsvToMap.read(resource);

        // Verify the results
        assertNotNull(result);
        assertEquals(3, result.size());

        // Check first row
        Map<String, String> firstRow = result.get(0);
        assertEquals("John", firstRow.get("name"));
        assertEquals("30", firstRow.get("age"));
        assertEquals("New York", firstRow.get("city"));

        // Check second row
        Map<String, String> secondRow = result.get(1);
        assertEquals("Jane", secondRow.get("name"));
        assertEquals("25", secondRow.get("age"));
        assertEquals("San Francisco", secondRow.get("city"));

        // Check third row
        Map<String, String> thirdRow = result.get(2);
        assertEquals("Bob", thirdRow.get("name"));
        assertEquals("40", thirdRow.get("age"));
        assertEquals("Chicago", thirdRow.get("city"));
    }

    @Test
    public void testReadEmptyCsv() throws IOException {
        // Prepare test data with only headers
        String csvContent = "name,age,city\n";

        ByteArrayResource resource = new ByteArrayResource(csvContent.getBytes(StandardCharsets.UTF_8));

        // Execute the method
        List<Map<String, String>> result = CsvToMap.read(resource);

        // Verify the results
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testReadCsvWithMissingValues() throws IOException {
        // Prepare test data with missing values
        String csvContent = """
                name,age,city
                John,,New York
                Jane,25,
                ,40,Chicago""";

        ByteArrayResource resource = new ByteArrayResource(csvContent.getBytes(StandardCharsets.UTF_8));

        // Execute the method
        List<Map<String, String>> result = CsvToMap.read(resource);

        // Verify the results
        assertNotNull(result);
        assertEquals(3, result.size());

        // Check first row
        Map<String, String> firstRow = result.get(0);
        assertEquals("John", firstRow.get("name"));
        assertEquals("", firstRow.get("age"));
        assertEquals("New York", firstRow.get("city"));

        // Check second row
        Map<String, String> secondRow = result.get(1);
        assertEquals("Jane", secondRow.get("name"));
        assertEquals("25", secondRow.get("age"));
        assertEquals("", secondRow.get("city"));

        // Check third row
        Map<String, String> thirdRow = result.get(2);
        assertEquals("", thirdRow.get("name"));
        assertEquals("40", thirdRow.get("age"));
        assertEquals("Chicago", thirdRow.get("city"));
    }
}
