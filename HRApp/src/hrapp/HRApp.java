/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hrapp;

/**
 *
 * @author shatshai@gmail.com
 */
public class HRApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("HR App start");

        Department dept = new Department("Department Education");

        System.out.println("Department: " + dept.toString());

        dept.addEmployee(new Employee(101, "Ann", 1234.56));
        dept.addEmployee(new Employee(102, "Bob", 1200.34));
        dept.addEmployee(new Employee(103, "Ray", 1122.33));

        Employee emp1 = dept.getEmployeeById(1101);
        if (emp1 != null) {
            System.out.println(emp1.toString());
        }

        for (Employee employee : dept.getEmployees()) {
            System.out.println(employee.toString());
        }

        System.out.println("Total salary: " + dept.getTotalSalary());
        System.out.println("Average salary: " + dept.average());
    }

}
