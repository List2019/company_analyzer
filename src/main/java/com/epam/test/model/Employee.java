package com.epam.test.model;

import java.math.BigDecimal;

public record Employee(
        Integer id,
        String firstName,
        String lastName,
        BigDecimal salary,
        Integer managerId
) {
    public String getDisplayName() {
        return firstName + " " + lastName;
    }
}
