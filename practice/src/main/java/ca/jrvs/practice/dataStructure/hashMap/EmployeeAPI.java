package ca.jrvs.practice.dataStructure.hashMap;

import java.util.*;

public class EmployeeAPI {
    public static void main(String[] args){
        Map<Employee, List<String>> employeeList = new HashMap<Employee, List<String>>();
        Employee ore = new Employee(1, "Ore", 25, 50000);
        List<String> oreEmployers = Arrays.asList("Eq", "Honda", "Mar");
        employeeList.put(ore, oreEmployers);

        Employee tina = new Employee(2, "Tina", 24, 40000);
        List<String> tinaEmployers = Arrays.asList("Liv", "Honda", "Super");
        employeeList.put(tina, tinaEmployers);

        System.out.println("Hashcode: " + ore.hashCode());
        System.out.println("Value" + employeeList.get(ore).toString());


    }
}
