package Streams;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeStream {
    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("Priya",25,25000,"IT"),
                new Employee("Rahul", 25,2500 ,"HR"),
                new Employee("Amit", 25,5000, "HR"),
                new Employee("Neha", 25,15000 ,"IT"),
                new Employee("John", 25,35000 ,"IT")
        );

        List<Employee> result = employees.stream().filter(emp->emp.getSalary()>5000)
                .toList();

        result.forEach(emp -> System.out.println(emp.getName()));
    }

    public static class Employee {
        private String name;
        private int age;
        private double salary;
        private String department;

        public Employee(String name, int age, double salary, String department) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.department = department;
        }

        public String getName() {
            return name;
        }
        public String getDepartment(){
            return department;

        }

        public double getSalary() {
            return salary;
        }
    }
}
