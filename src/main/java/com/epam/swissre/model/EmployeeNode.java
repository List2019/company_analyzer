package com.epam.swissre.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeNode {
    Employee employee;
    List<EmployeeNode> subordinates;

    public EmployeeNode(Employee employee) {
        this.employee = employee;
        this.subordinates = new ArrayList<>();
    }

    public void addSubordinate(EmployeeNode employeeNode) {
        subordinates.add(employeeNode);
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<EmployeeNode> getSubordinates() {
        return subordinates;
    }
}
