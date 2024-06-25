package com.epam.swissre.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.epam.swissre.model.Employee;

public class CompanyAnalyzeService {
    private final SalaryService salaryService;
    private final ReportLineService reportLineService;
    private final PrintService printService;

    public CompanyAnalyzeService(SalaryService salaryService, ReportLineService reportLineService, PrintService printService) {
        this.salaryService = salaryService;
        this.reportLineService = reportLineService;
        this.printService = printService;
    }

    public void analyzeCompany(String csvFileName) {
        var csvReaderService = new CSVReaderService();
        List<Employee> employees = csvReaderService.readCSV(csvFileName);

        analyzeSalary(employees);
        analyzeReportingLine(employees);
    }

    private void analyzeSalary(List<Employee> employees) {
        Map<Employee, BigDecimal> managerSubordinatesAverageSalary = salaryService.getManagerSubordinatesAverageSalary(employees);

        Map<Employee, BigDecimal> underpaidManagers = salaryService.getUnderpaidManagers(managerSubordinatesAverageSalary);
        Map<Employee, BigDecimal> overpaidManagers = salaryService.getOverpaidManagers(managerSubordinatesAverageSalary);

        printService.printUnderpaidManagers(underpaidManagers);
        printService.printOverpaidManagers(overpaidManagers);
    }

    private void analyzeReportingLine(List<Employee> employees) {
        Map<Employee, Integer> employeesWithLongReportingLine = reportLineService.getLongReportLineEmployees(employees);
        printService.printEmployeesWithLongReportingLine(employeesWithLongReportingLine);
    }
}
