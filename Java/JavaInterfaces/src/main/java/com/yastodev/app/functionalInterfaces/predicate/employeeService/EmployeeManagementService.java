package com.yastodev.app.functionalInterfaces.predicate.employeeService;

import com.yastodev.app.functionalInterfaces.utils.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class EmployeeManagementService {
    private List<Employee> employees;

    public EmployeeManagementService() {
        employees = new ArrayList<>();

        employees.add(new Employee("Alice", 30, 75000.0, 1001L, List.of("Java", "Python")));
        employees.add(new Employee("Bob", 25, 65000.0, 1002L,  List.of("JavaScript", "React")));
        employees.add(new Employee("Charlie", 35, 85000.0, 1003L,  List.of("Java", "Devops", "Cloud")));
    }

    // Predicate Example: Filtering Employees
    public List<Employee> filterEmployees(Predicate<Employee> filterCriteria) {
        return employees.stream()
                .filter(filterCriteria)
                .collect(Collectors.toList());
    }

    public List<Employee> filterUsersByMultipleCriteria(BiPredicate<Integer,Double> complexFilter){
        return employees.stream()
                .filter(employee -> complexFilter.test(employee.getAge(), employee.getSalary()))
                .collect(Collectors.toList());
    }

    public List<Integer> filterAges(IntPredicate agePredicate){
        return employees.stream()
                .map(Employee::getAge)
                .filter(agePredicate::test)
                .collect(Collectors.toList());
    }

    public List<Double> filterSalaries(DoublePredicate salaryPredicate) {
        return employees.stream()
                .map(Employee::getSalary)
                .filter(salaryPredicate::test)
                .collect(Collectors.toList());
    }

    public List<Long> filterEmployeeIds(LongPredicate employeeIdPredicate) {
        return employees.stream()
                .map(Employee::getEmployeeId)
                .filter(employeeIdPredicate::test)
                .collect(Collectors.toList());
    }




}
