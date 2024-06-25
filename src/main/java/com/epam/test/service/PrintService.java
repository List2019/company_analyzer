package com.epam.test.service;

import static com.epam.test.utils.Constants.EARNS_LESS;
import static com.epam.test.utils.Constants.EARNS_MORE;
import static com.epam.test.utils.Constants.SEPARATOR;

import java.math.BigDecimal;
import java.util.Map;

import com.epam.test.model.Employee;

public class PrintService {

    public void printUnderpaidManagers(Map<Employee, BigDecimal> managers) {
        printManagers(managers, EARNS_LESS);
    }

    public void printOverpaidManagers(Map<Employee, BigDecimal> managers) {
        printManagers(managers, EARNS_MORE);
    }

    public void printEmployeesWithLongReportingLine(Map<Employee, Integer> employeesWithLongReportingLine) {
        if (employeesWithLongReportingLine.isEmpty()) {
            return;
        }

        System.out.println();
        System.out.println("Employees which have more than 4 managers between them and the CEO:");
        System.out.println(SEPARATOR);

        employeesWithLongReportingLine.forEach((employee, reportingLineLength) -> {
            System.out.print(employee.getDisplayName());
            System.out.println(" have " + reportingLineLength + " managers between them and CEO");
        });

        System.out.println(SEPARATOR);
        System.out.println();
    }

    private static void printManagers(Map<Employee, BigDecimal> managers, String displayText) {
        if (managers.isEmpty()) {
            return;
        }

        System.out.println();
        System.out.println("Managers which earns " + displayText + " than they should:");
        System.out.println(SEPARATOR);

        managers.forEach((manager, value) -> {
            System.out.print(manager.getDisplayName());
            System.out.println(" earns " + displayText + " than they should, on " + value);
        });

        System.out.println(SEPARATOR);
        System.out.println();
    }
}
