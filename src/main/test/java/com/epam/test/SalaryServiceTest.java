package com.epam.test;

import static com.epam.test.TestConstants.EMPLOYEE_LAST_NAME;
import static com.epam.test.TestConstants.EMPLOYEE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.epam.test.model.Employee;
import com.epam.test.service.SalaryService;

class SalaryServiceTest {

    private final SalaryService salaryService = new SalaryService();

    @Test
    void getManagerSubordinatesAverageSalaryTest() {
        List<Employee> employees = getEmployees();
        Map<Employee, BigDecimal> managerSubordinatesAverageSalary = salaryService.getManagerSubordinatesAverageSalary(employees);

        assertFalse(managerSubordinatesAverageSalary.isEmpty());
        managerSubordinatesAverageSalary.forEach((employee, avgSubordinatesSalary) -> assertNotNull(avgSubordinatesSalary));

        // Expected value was manually calculated based on data from getEmployees method
        assertEquals(BigDecimal.valueOf(42500L).setScale(1, RoundingMode.HALF_UP), managerSubordinatesAverageSalary.get(employees.getFirst()));
    }

    @Test
    void getUnderpaidManagersTest() {
        Map<Employee, BigDecimal> managersWithSmallSalaries = getManagersWithSmallSalaries();
        Map<Employee, BigDecimal> underpaidManagers = salaryService.getUnderpaidManagers(managersWithSmallSalaries);

        assertFalse(underpaidManagers.isEmpty());

        underpaidManagers.forEach((manager, underpaidAmount) -> {
            BigDecimal avgSubordinatesSalary = managersWithSmallSalaries.get(manager);
            BigDecimal avgSubordinatesSalaryPlus20Percent = avgSubordinatesSalary.multiply(BigDecimal.valueOf(1.2));
            assertEquals(avgSubordinatesSalaryPlus20Percent.subtract(manager.salary()), underpaidAmount);
        });
    }

    @Test
    void getOverpaidManagersTest() {
        Map<Employee, BigDecimal> managersWithSmallSalaries = getManagersWithHighSalaries();
        Map<Employee, BigDecimal> overpaidManagers = salaryService.getOverpaidManagers(managersWithSmallSalaries);

        assertFalse(overpaidManagers.isEmpty());

        overpaidManagers.forEach((manager, overpaidAmount) -> {
            BigDecimal avgSubordinatesSalary = managersWithSmallSalaries.get(manager);
            BigDecimal avgSubordinatesSalaryPlus50Percent = avgSubordinatesSalary.multiply(BigDecimal.valueOf(1.5));
            assertEquals(manager.salary().subtract(avgSubordinatesSalaryPlus50Percent), overpaidAmount);
        });
    }

    private List<Employee> getEmployees() {
        return List.of(
          new Employee(1, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(50000L), null),
          new Employee(2, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(40000L), 1),
          new Employee(3, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(33400L), 2),
          new Employee(4, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(28500L), 2),
          new Employee(5, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(45000L), 1),
          new Employee(6, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(45000L), 1),
          new Employee(7, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(40000L), 1)
        );
    }

    private Map<Employee, BigDecimal> getManagersWithSmallSalaries() {
        var manager1 = new Employee(2, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(30000L), 1);
        var manager2 = new Employee(3, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(35000L), 1);
        var manager3 = new Employee(4, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(40000L), 1);

        Map<Employee, BigDecimal> managerToSubordinatesAverageSalary = new HashMap<>();
        managerToSubordinatesAverageSalary.put(manager1, BigDecimal.valueOf(40000L));
        managerToSubordinatesAverageSalary.put(manager2, BigDecimal.valueOf(35500L));
        managerToSubordinatesAverageSalary.put(manager3, BigDecimal.valueOf(35000L));

        return managerToSubordinatesAverageSalary;
    }

    private Map<Employee, BigDecimal> getManagersWithHighSalaries() {
        var manager1 = new Employee(2, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(60000L), 1);
        var manager2 = new Employee(3, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(70000L), 1);
        var manager3 = new Employee(4, EMPLOYEE_NAME, EMPLOYEE_LAST_NAME, BigDecimal.valueOf(80000L), 1);

        Map<Employee, BigDecimal> managerToSubordinatesAverageSalary = new HashMap<>();
        managerToSubordinatesAverageSalary.put(manager1, BigDecimal.valueOf(40000L));
        managerToSubordinatesAverageSalary.put(manager2, BigDecimal.valueOf(35500L));
        managerToSubordinatesAverageSalary.put(manager3, BigDecimal.valueOf(35000L));

        return managerToSubordinatesAverageSalary;
    }
}
