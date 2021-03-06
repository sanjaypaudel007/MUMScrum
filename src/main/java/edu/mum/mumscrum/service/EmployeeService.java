package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.entity.Employee;


public interface EmployeeService {
	
	public void addEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);

	public void deleteEmployee(int employeeId);

	public Employee getEmployee(int employeeId);
	
	public List<Employee> getAllEmployee();

	public Employee getEmployeeDetail(String username);

	public Employee validateEmployee(Employee employee);
	
	public boolean checkUsername(String username);

	public String encryptPass(String password);

	public List<Employee> getAllDevelopers();

	public List<Employee> getAllTesters();

	public boolean changePassword(String username, String oldPassword, String password);
}
