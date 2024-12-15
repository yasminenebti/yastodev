package com.yastodev.app.functionalInterfaces.predicate.employeeService;

import com.yastodev.app.functionalInterfaces.utils.Employee;

import java.util.List;
import java.util.function.Predicate;

public class EmployeeManagementDemo {
    public static void main(String[] args) {
        EmployeeManagementService employeeManagementService = new EmployeeManagementService();

        //1. Filter User By Age
        System.out.println("\nFiltering employees over 30");
        List<Employee> employeesOver30 = employeeManagementService.filterEmployees(employee -> employee.getAge() > 30);
        employeesOver30.forEach(System.out::println);

        //1. Filter User By Skill
        System.out.println("\nFiltering java employees by skill");
        List<Employee> javaSkilledEmployees = employeeManagementService.filterEmployees(employee -> employee.getSkills().contains("Java"));
        javaSkilledEmployees.forEach(System.out::println);

        // 3. BiPredicate Example: Complex Filtering
        System.out.println("\nEmployees over 28 with Salary above 70000");
        List<Employee> employeesOver28WithSalaryAbove70000 = employeeManagementService.filterUsersByMultipleCriteria((age, salary) -> age > 28 && salary > 70000);
        employeesOver28WithSalaryAbove70000.forEach(System.out::println);

        // 4. IntPredicate: Filter Ages
        System.out.println("\nAges between 25 and 35:");
        List<Integer> filteredAges = employeeManagementService.filterAges(age -> age >= 25 && age <= 35);
        filteredAges.forEach(System.out::println);
        System.out.println(filteredAges);

        // 5. DoublePredicate: Filter Salaries
        System.out.println("\nSalaries above 70000:");
        List<Double> filteredSalaries = employeeManagementService.filterSalaries(salary -> salary > 70000);
        filteredSalaries.forEach(System.out::println);

        // 6. LongPredicate: Filter Employee IDs
        System.out.println("\nEmployee IDs above 1002:");
        List<Long> filteredEmployeeIds = employeeManagementService.filterEmployeeIds(
                employeeId -> employeeId > 1002L);
        System.out.println(filteredEmployeeIds);

        // 7. Combining Predicates
        Predicate<Employee> isYoung = user -> user.getAge() < 30;
        Predicate<Employee> hasJavaSkills = user -> user.getSkills().contains("Java");

        System.out.println("\nYoung Java Developers:");
        List<Employee> youngJavaDevelopers = employeeManagementService.filterEmployees(
                isYoung.and(hasJavaSkills));
        youngJavaDevelopers.forEach(System.out::println);



    }
}

