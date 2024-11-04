package com.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestEmp {

	public static void main(String[] args) {
		
	
	List<Employee> employees = Arrays.asList(
            new Employee(1,"Rahul Gupta", "Male", "Infosys","hyd"),
            new Employee(2,"Priya Patil", "Female", "Infosys","vizag"),
            new Employee(3,"Amit Raj", "Male", "TCS","hyd"),
            new Employee(4,"Deepika Sharma", "Female", "TCS","vizag"),
            new Employee(5,"Vijay Kumar", "Male", "Infosys","vizag")
        );
	
	long count = employees.stream().filter(e->"Male".equals(e.getGender())&& "Infosys".equals(e.getOrganization())).count();
	
	System.out.println("male employees in infosys="+count);
	Map<String, List<Employee>> collect = employees.stream().filter(e->"vizag".equals(e.getCity())).collect(Collectors.groupingBy(Employee::getCity));
	System.out.println(collect);
}
}

