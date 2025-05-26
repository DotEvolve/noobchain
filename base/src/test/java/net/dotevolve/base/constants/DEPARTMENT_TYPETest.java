package net.dotevolve.base.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DEPARTMENT_TYPETest {

    @Test
    void enumValuesShouldHaveCorrectIdValue() {
        assertEquals("0", DEPARTMENT_TYPE.UNKNOWN.getId());
    }

    @Test
    void enumValuesShouldHaveCorrectDepartmentValue() {
        assertEquals("Unknown", DEPARTMENT_TYPE.UNKNOWN.getDepartment());
    }

    @Test
    void enumValuesShouldNotHaveNullId() {
        for (DEPARTMENT_TYPE departmentType : DEPARTMENT_TYPE.values()) {
            assertNotNull(departmentType.getId());
        }
    }

    @Test
    void enumValuesShouldNotHaveNullDepartment() {
        for (DEPARTMENT_TYPE departmentType : DEPARTMENT_TYPE.values()) {
            assertNotNull(departmentType.getDepartment());
        }
    }
}