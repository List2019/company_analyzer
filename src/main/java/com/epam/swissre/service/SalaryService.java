package com.epam.swissre.service;

import static com.epam.swissre.utils.Constants.FIFTY_PERCENT_INCREASE;
import static com.epam.swissre.utils.Constants.TWENTY_PERCENT_INCREASE;
import static java.util.stream.Collectors.groupingBy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.epam.swissre.model.Employee;

public class SalaryService {

    public Map<Employee, BigDecimal> getManagerSubordinatesAverageSalary(List<Employee> employees) {
        Map<Employee, BigDecimal> managerSubordinatesAverageSalary = new HashMap<>();
        Map<Integer, Employee> employeesMap = employees.stream().collect(Collectors.toMap(Employee::id, Function.identity()));

        Map<Integer, List<Employee>> managerIdWithSubordinates = employees.stream()
                .filter(employee -> employee.managerId() != null)
                .collect(groupingBy(Employee::managerId));

        managerIdWithSubordinates.forEach((managerId, subordinates) -> {
            double averageSalary = subordinates.stream()
                    .mapToDouble(subordinate -> subordinate.salary().doubleValue())
                    .average()
                    .orElseThrow();
            Employee manager = employeesMap.get(managerId);
            managerSubordinatesAverageSalary.put(manager, BigDecimal.valueOf(averageSalary));
        });

        return managerSubordinatesAverageSalary;
    }

    /**
     * @return key - underpaid managers, value - underpaid amount
     */
    public Map<Employee, BigDecimal> getUnderpaidManagers(Map<Employee, BigDecimal> managerSubordinatesAverageSalary) {
        return managerSubordinatesAverageSalary.entrySet().stream()
                .filter(this::isManagerUnderpaid)
                .collect(Collectors.toMap(Map.Entry::getKey, this::getUnderpaidAmount));
    }

    /**
     * @return is (subordinates avg + 20%) bigger than manager salary
     */
    private boolean isManagerUnderpaid(Map.Entry<Employee, BigDecimal> entry) {
        var avgSubordinatesSalaryPlus20Percent = increaseEntryValue(entry, TWENTY_PERCENT_INCREASE);
        BigDecimal managerSalary = entry.getKey().salary();

        return avgSubordinatesSalaryPlus20Percent.compareTo(managerSalary) > 0;
    }

    private BigDecimal getUnderpaidAmount(Map.Entry<Employee, BigDecimal> entry) {
        var avgSubordinatesSalaryPlus20Percent = increaseEntryValue(entry, TWENTY_PERCENT_INCREASE);
        BigDecimal managerSalary = entry.getKey().salary();

        return avgSubordinatesSalaryPlus20Percent.subtract(managerSalary);
    }

    /**
     * @return key - overpaid managers, value - overpaid amount
     */
    public Map<Employee, BigDecimal> getOverpaidManagers(Map<Employee, BigDecimal> managerSubordinatesAverageSalary) {
        return managerSubordinatesAverageSalary.entrySet().stream()
                .filter(this::isManagerOverpaid)
                .collect(Collectors.toMap(Map.Entry::getKey, this::getOverpaidAmount));
    }

    /**
     * @return is manager salary more than 50% from subordinates average
     */
    private boolean isManagerOverpaid(Map.Entry<Employee, BigDecimal> entry) {
        var avgSubordinatesSalaryPlus50Percent = increaseEntryValue(entry, FIFTY_PERCENT_INCREASE);
        BigDecimal managerSalary = entry.getKey().salary();

        return managerSalary.compareTo(avgSubordinatesSalaryPlus50Percent) > 0;
    }

    private BigDecimal getOverpaidAmount(Map.Entry<Employee, BigDecimal> entry) {
        var avgSubordinatesSalaryPlus50Percent = increaseEntryValue(entry, FIFTY_PERCENT_INCREASE);
        BigDecimal managerSalary = entry.getKey().salary();

        return managerSalary.subtract(avgSubordinatesSalaryPlus50Percent);
    }

    private BigDecimal increaseEntryValue(Map.Entry<Employee, BigDecimal> entry, BigDecimal increaseFactor) {
        return entry.getValue().multiply(increaseFactor);
    }
}
