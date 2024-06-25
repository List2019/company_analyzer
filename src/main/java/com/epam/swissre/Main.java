package com.epam.swissre;

import static com.epam.swissre.utils.Constants.CSV_FILE_NAME_WAS_NOT_PROVIDED_ERROR_MESSAGE;

import com.epam.swissre.exception.CSVFileNameMissingException;
import com.epam.swissre.service.CompanyAnalyzeService;
import com.epam.swissre.service.PrintService;
import com.epam.swissre.service.ReportLineService;
import com.epam.swissre.service.SalaryService;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0 || args[0] == null) {
            throw new CSVFileNameMissingException(CSV_FILE_NAME_WAS_NOT_PROVIDED_ERROR_MESSAGE);
        }
        String employeesCSVFileName = args[0];

        var companyAnalyzeService = new CompanyAnalyzeService(new SalaryService(), new ReportLineService(), new PrintService());
        companyAnalyzeService.analyzeCompany(employeesCSVFileName);
    }
}
