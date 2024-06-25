package com.epam.swissre;

import static com.epam.swissre.TestConstants.EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.epam.swissre.service.PrintService;

class PrintServiceTest {
    private final PrintService printService = new PrintService();


    @Test
    void shouldHandleEmptyUnderpaidManagers() {
        try {
            printService.printUnderpaidManagers(new HashMap<>());
        } catch (Exception e) {
            fail(EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN);
        }
    }

    @Test
    void shouldHandleEmptyOverpaidManagers() {
        try {
            printService.printOverpaidManagers(new HashMap<>());
        } catch (Exception e) {
            fail(EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN);
        }
    }

    @Test
    void shouldHandleEmptyEmployeesWithLongReportingLine() {
        try {
            printService.printEmployeesWithLongReportingLine(new HashMap<>());
        } catch (Exception e) {
            fail(EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN);
        }
    }
}
