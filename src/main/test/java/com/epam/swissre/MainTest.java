package com.epam.swissre;

import static com.epam.swissre.TestConstants.EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN;
import static com.epam.swissre.utils.Constants.CSV_FILE_NAME_WAS_NOT_PROVIDED_ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.epam.swissre.exception.CSVFileNameMissingException;

class MainTest {

    @Test
    void employeeCSVFileNameWasNotProvided() {
        CSVFileNameMissingException exception = assertThrows(CSVFileNameMissingException.class, () -> Main.main(new String[0]));

        assertEquals(CSV_FILE_NAME_WAS_NOT_PROVIDED_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void analyzeWasCompletedSuccessfully() {
        try {
            Main.main(new String[] {"mock_employees.csv"});
        } catch (Exception e) {
            fail(EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN);
        }
    }
}
