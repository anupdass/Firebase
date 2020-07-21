package com.example.firebase;

public class Employee {

    private String emId;
    private String name;
    private double salary;

    public Employee() {
    }

    public Employee(String emId, String name, double salary) {
        this.emId = emId;
        this.name = name;
        this.salary = salary;
    }

    public String getEmId() {
        return emId;
    }

    public void setEmId(String emId) {
        this.emId = emId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
