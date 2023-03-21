package ca.jrvs.practice.dataStructure.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeSort {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(1, "Folake", 25, 70000));
        employees.add(new Employee(2, "Tom", 30, 80000));
        employees.add(new Employee(3, "Priscillia", 25, 70000));
        employees.add(new Employee(4, "Mark", 65, 50000));
        employees.add(new Employee(5, "Kim", 25, 60000));

        List<Employee> employees2 = new ArrayList<Employee>();
        employees2 = employees;

        Collections.sort(employees);

        Collections.sort(employees2, new Comparator<Employee>() {
            @Override
            public int compare(Employee e, Employee t1) {
                if(e == t1 || e.getAge() == t1.getAge() && e.getSalary() == t1.getSalary()){
                    return 0;
                }
                if(e.getAge() == t1.getAge()){
                    return e.getSalary() > t1.getSalary() ? 1 : -1;
                }
                return e.getAge() > t1.getAge() ? 1 : -1;
            }
        });

        employees.stream().forEach(e -> System.out.println(e.getId() + ", " + e.getName() + ", " + e.getAge() + ", " + e.getSalary()));
        employees2.stream().forEach(e -> System.out.println(e.getId() + ", " + e.getName() + ", " + e.getAge() + ", " + e.getSalary()));
    }
}

