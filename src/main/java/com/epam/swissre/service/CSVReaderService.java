package com.epam.swissre.service;

import static com.epam.swissre.utils.Constants.CSV_FILE_READING_ERROR_MESSAGE;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.epam.swissre.exception.CSVFileNotFoundException;
import com.epam.swissre.exception.CSVFileReadingException;
import com.epam.swissre.model.Employee;

public class CSVReaderService {

    private static final String SPLITTER = ",";

    public List<Employee> readCSV(String fileName) {
        List<Employee> employees = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new CSVFileNotFoundException(fileName);
        }

        try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(SPLITTER);

                if (columns[0].chars().allMatch(Character::isLetter)) {
                    continue;
                }

                Integer id = Integer.valueOf(columns[0]);
                String firstName = columns[1];
                String lastName = columns[2];
                BigDecimal salary = new BigDecimal(columns[3]);
                Integer managerId = getManagerId(columns);

                var employee = new Employee(
                        id,
                        firstName,
                        lastName,
                        salary,
                        managerId
                );

                employees.add(employee);
            }
        } catch (Exception e) {
            throw new CSVFileReadingException(CSV_FILE_READING_ERROR_MESSAGE);
        }

        return employees;
    }

    private Integer getManagerId(String[] columns) {
        if (columns.length == 4) {
            return null;
        }
        return Integer.parseInt(columns[4]);
    }
}
