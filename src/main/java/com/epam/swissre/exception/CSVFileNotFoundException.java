package com.epam.swissre.exception;

import static com.epam.swissre.utils.Constants.CSV_FILE_WAS_NOT_FOUND_ERROR_MESSAGE;

public class CSVFileNotFoundException extends RuntimeException {

    public CSVFileNotFoundException(String fileName) {
        super(String.format(CSV_FILE_WAS_NOT_FOUND_ERROR_MESSAGE, fileName));
    }
}
