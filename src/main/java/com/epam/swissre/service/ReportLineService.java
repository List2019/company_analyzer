package com.epam.swissre.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.epam.swissre.model.Employee;
import com.epam.swissre.model.EmployeeNode;

public class ReportLineService {

    public static final int MAX_REPORTING_LINE_DEPTH = 4;

    public Map<Employee, Integer> getLongReportLineEmployees(List<Employee> employees) {
        EmployeeNode ceoNode = buildCEONode(employees);
        Map<Employee, Integer> employeeReportingLineDepth = findReportingLineDepth(ceoNode);

        return employeeReportingLineDepth.entrySet().stream()
                .filter(entry -> entry.getValue() > MAX_REPORTING_LINE_DEPTH)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private EmployeeNode buildCEONode(List<Employee> employees) {
        Map<Integer, EmployeeNode> employeeIdToNode = new HashMap<>();
        EmployeeNode ceo = null;

        for (Employee employee : employees) {
            var node = new EmployeeNode(employee);
            employeeIdToNode.put(employee.id(), node);
            if (employee.managerId() == null) {
                ceo = node;
            }
        }

        addSubordinatesForManagers(employeeIdToNode, employees);

        return ceo;
    }

    private void addSubordinatesForManagers(Map<Integer, EmployeeNode> employeeIdToNode, List<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.managerId() != null) {
                EmployeeNode managerNode = employeeIdToNode.get(employee.managerId());
                EmployeeNode employeeNode = employeeIdToNode.get(employee.id());
                managerNode.addSubordinate(employeeNode);
            }
        }
    }

    private Map<Employee, Integer> findReportingLineDepth(EmployeeNode ceo) {
        Map<Employee, Integer> reportingLineDepth = new HashMap<>();
        recursivelyFindReportingLineDepth(ceo, -1, reportingLineDepth);
        return reportingLineDepth;
    }

    /**
     * Here we are using Depth-first search (DFS) algorithm for iteration over all node.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Depth-first_search">Depth-first search</a>
     */
    private void recursivelyFindReportingLineDepth(EmployeeNode node, int depth, Map<Employee, Integer> reportingLineDepth) {
        reportingLineDepth.put(node.getEmployee(), depth);
        for (EmployeeNode subordinate : node.getSubordinates()) {
            recursivelyFindReportingLineDepth(subordinate, depth + 1, reportingLineDepth);
        }
    }
}
