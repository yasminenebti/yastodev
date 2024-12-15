package com.yastodev.app.functionalInterfaces.utils;

import java.util.List;

public class Employee {
    private String name;
    private int age;
    private double salary;
    private long employeeId;
    private List<String> skills;

    public Employee(String name, int age, double salary, long employeeId, List<String> skills) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.employeeId = employeeId;
        this.skills = skills;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public long getEmployeeId() { return employeeId; }
    public List<String> getSkills() { return skills; }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + ", salary=" + salary +
                ", employeeId=" + employeeId + ", skills=" + skills + "}";
    }


}
