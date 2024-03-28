/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hrapp;

/**
 *
 * @author shatshai@gmail.com
 */
public class Department {

    private String name;
    private Employee[] employees = new Employee[10];
    private int lastAddEmployeeIndex = -1;

    public Department(String name) {
        this.name = name;
    }

    public Employee[] getEmployees() {
        Employee[] actualEmployees = new Employee[lastAddEmployeeIndex + 1];
        for (int i = 0; i < actualEmployees.length; i++) {
            actualEmployees[i] = employees[i];
        }
        
        return actualEmployees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addEmployee(Employee employee) {
        if (lastAddEmployeeIndex < employees.length) {
            lastAddEmployeeIndex++;
            employees[lastAddEmployeeIndex] = employee;
        }
    }

    public void setEmployees(Employee[] employees) {
        if (employees.length <= 10) {
            this.employees = employees;
        }
    }

    public int getNumberOfEmployee() {
        return lastAddEmployeeIndex + 1;
    }

    public Employee getEmployeeById(int id) {
        for (Employee employee : getEmployees()) {
            if (employee.getId() == id) {
                return employee;
            }
        }

        return null;
    }

    public double getTotalSalary() {
        double total = 0;
        for (Employee employee : getEmployees()) {
            total += employee.getSalary();
        }

        return total;
    }

    public double average() {
        return (getNumberOfEmployee() > 0)
                ? getTotalSalary() / getNumberOfEmployee() : 0;
    }

    @Override
    public String toString() {
        return getName();
    }
}
