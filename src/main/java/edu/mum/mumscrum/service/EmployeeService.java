package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.entity.Employee;


public interface EmployeeService {
	public void add(Employee employee);

	public void edit(Employee employee);

	public void delete(int employeeId);

	public Employee getEmployee(int employeeId);

	public List<Employee> getAllEmployee();

	public Employee validateEmployee(Employee employee);

	public Employee getEmployeeDetail(String username);

	public boolean checkUsername(String username, int employeeId);

	public String encryptPass(String password);

	public boolean checkPassword(String password, String encodedPassword);

	public List<Employee> getEmployeeByName(String name);

	public List<Employee> getScrumMasters();

	public List<Employee> getAllDevelopers();

	public List<Employee> getAllTesters();
}
