package net.dotevolve.base.data.commons.object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StateCodesMapTest {

    private StateCodesMap stateCodesMap;

    @BeforeEach
    void setUp() {
        stateCodesMap = new StateCodesMap();
        stateCodesMap.populateStateCodeToNameMap();
        stateCodesMap.populateFipsCodeToStateCode();
    }

    /**
     * Test to ensure the correct state name is returned for a valid state code.
     */
    @Test
    void testGetStateNameByCode_ValidCode() {
        String stateName = stateCodesMap.getStateNameByCode("ca");
        assertEquals("California", stateName);
    }

    /**
     * Test to ensure the default value "Others" is returned for an invalid state code.
     */
    @Test
    void testGetStateNameByCode_InvalidCode() {
        String stateName = stateCodesMap.getStateNameByCode("xx");
        assertEquals("Others", stateName);
    }

    /**
     * Test to ensure the state name is returned correctly when the state code is in uppercase.
     */
    @Test
    void testGetStateNameByCode_UpperCaseCode() {
        String stateName = stateCodesMap.getStateNameByCode("MI");
        assertEquals("Michigan", stateName);
    }

    /**
     * Test to ensure "Others" is returned when the input state code is null.
     */
    @Test
    void testGetStateNameByCode_NullCode() {
        assertThrows(NullPointerException.class,
                () -> stateCodesMap.getStateNameByCode(null),
                "Expected NullPointerException when state code is null");
    }

    /**
     * Test to ensure "Others" is returned when the input state code is empty.
     */
    @Test
    void testGetStateNameByCode_EmptyCode() {
        String stateName = stateCodesMap.getStateNameByCode("");
        assertEquals("Others", stateName);
    }

    @Test
    void testGetStateCodeByFipsCode_ValidCode() {
        String stateCode = stateCodesMap.getStateCodeByFipsCode("6");
        assertEquals("CA", stateCode);
    }

    @Test
    void testGetStateCodeByFipsCode_InvalidCode() {
        String stateCode = stateCodesMap.getStateCodeByFipsCode("99");
        assertEquals("__", stateCode);
    }
}