package com.epam.test;

import static com.epam.test.utils.Constants.CSV_FILE_NAME_WAS_NOT_PROVIDED_ERROR_MESSAGE;

import com.epam.test.exception.CSVFileNameMissingException;
import com.epam.test.service.CompanyAnalyzeService;
import com.epam.test.service.PrintService;
import com.epam.test.service.ReportLineService;
import com.epam.test.service.SalaryService;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0 || args[0] == null) {
            throw new CSVFileNameMissingException(CSV_FILE_NAME_WAS_NOT_PROVIDED_ERROR_MESSAGE);
        }
        String employeeCSVFileName = args[0];

        var companyAnalyzeService = new CompanyAnalyzeService(new SalaryService(), new ReportLineService(), new PrintService());
        companyAnalyzeService.analyzeCompany(employeeCSVFileName);
    }
}
