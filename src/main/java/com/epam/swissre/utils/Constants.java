package com.epam.swissre.utils;

import java.math.BigDecimal;

public class Constants {
    public static final String CSV_FILE_NAME_WAS_NOT_PROVIDED_ERROR_MESSAGE = "CSV file name was not provided";
    public static final String CSV_FILE_WAS_NOT_FOUND_ERROR_MESSAGE = "CSV file %s wasn't found in classpath";
    public static final String CSV_FILE_READING_ERROR_MESSAGE = "Error happened during reading CSV file, please check file structure";

    public static final BigDecimal TWENTY_PERCENT_INCREASE = BigDecimal.valueOf(1.2);
    public static final BigDecimal FIFTY_PERCENT_INCREASE = BigDecimal.valueOf(1.5);

    public static final String SEPARATOR = "----------------------------------------------------------";
    public static final String EARNS_LESS = "less";
    public static final String EARNS_MORE = "more";

    private Constants() {}
}
