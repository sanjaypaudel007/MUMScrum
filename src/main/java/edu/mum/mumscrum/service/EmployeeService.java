package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.entity.Employee;


public interface EmployeeService {
	public List<Employee> getAllEmployee();
	
	public boolean checkUsername(String username, int employeeId);

	public String encryptPass(String password);
	
	public void add(Employee employee);

	public Employee getEmployee(int employeeId);

	public Employee validateEmployee(Employee employee);

	public Employee getEmployeeDetail(String username);
}
