package com.model;

public class Employee {
	
	public Employee() {
	
	}
	int empId;
	String name;
	String Gender;
	String Organization;
	String city;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getOrganization() {
		return Organization;
	}
	public void setOrganization(String organization) {
		Organization = organization;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Employee(int empId, String name, String gender, String organization, String city) {
		super();
		this.empId = empId;
		this.name = name;
		Gender = gender;
		Organization = organization;
		this.city = city;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", Gender=" + Gender + ", Organization=" + Organization
				+ ", city=" + city + "]";
	}
	
	

}
