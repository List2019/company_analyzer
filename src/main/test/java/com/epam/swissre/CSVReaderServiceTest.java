package com.epam.swissre;

import static com.epam.swissre.utils.Constants.CSV_FILE_READING_ERROR_MESSAGE;
import static com.epam.swissre.utils.Constants.CSV_FILE_WAS_NOT_FOUND_ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.epam.swissre.exception.CSVFileNotFoundException;
import com.epam.swissre.exception.CSVFileReadingException;
import com.epam.swissre.model.Employee;
import com.epam.swissre.service.CSVReaderService;

class CSVReaderServiceTest {

    private final CSVReaderService csvReaderService = new CSVReaderService();

    @Test
    void csvFileWasNotFound() {
        String wrongFileName = "abcFileName.csv";
        CSVFileNotFoundException exception = assertThrows(CSVFileNotFoundException.class, () -> csvReaderService.readCSV(wrongFileName));

        String expectedErrorMessage = String.format(CSV_FILE_WAS_NOT_FOUND_ERROR_MESSAGE, wrongFileName);
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void csvFileIncorrectStructure() {
        String incorrectStructureFilename = "incorrect_structure.csv";
        CSVFileReadingException exception = assertThrows(CSVFileReadingException.class, () -> csvReaderService.readCSV(incorrectStructureFilename));

        assertEquals(CSV_FILE_READING_ERROR_MESSAGE, exception.getMessage());
    }


    @Test
    void csvFileWasSuccessfullyParsed() {
        List<Employee> employees = csvReaderService.readCSV("mock_employees.csv");

        assertEquals(11, employees.size());

        boolean isCEOPresent = employees.stream().anyMatch(employee -> employee.managerId() == null);
        assertTrue(isCEOPresent);
    }
}
