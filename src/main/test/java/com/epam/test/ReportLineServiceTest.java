package com.epam.test;

import static com.epam.test.TestConstants.EMPLOYEE_LAST_NAME;
import static com.epam.test.TestConstants.EMPLOYEE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.epam.test.model.Employee;
import com.epam.test.service.ReportLineService;

class ReportLineServiceTest {

    private final ReportLineService reportLineService = new ReportLineService();

    @Test
    void getLongReportLineEmployeesTest() {
        List<Employee> employees = getEmployees();
        Map<Employee, Integer> longReportLineEmployees = reportLineService.getLongReportLineEmployees(employees);

        // Expected value was manually calculated based on data from getEmployees method
        assertFalse(longReportLineEmployees.isEmpty());
        assertEquals(2, longReportLineEmployees.size());

        assertTrue(longReportLineEmployees.values().containsAll(List.of(5,6)));
    }

    private List<Employee> getEmployees() {
        return List.of(
                new Employee(1, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.ONE, null),
                new Employee(2, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.ONE, 1),

                new Employee(3, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.ONE, 2),
                new Employee(4, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.ONE, 2),

                new Employee(5, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.ONE, 3),
                new Employee(6, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.ONE, 5),
                new Employee(7, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.ONE, 6),
                new Employee(8, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.ONE, 7),
                new Employee(9, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.ONE, 8)
        );
    }
}
