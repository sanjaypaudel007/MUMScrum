package edu.mum.mumscrum.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.repository.EmployeeRepository;
import edu.mum.mumscrum.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@PreAuthorize(value="hasRole('ADMIN')")
	public void add(Employee employee) {
		
		employeeRepository.save(employee);

	}
	@PreAuthorize(value="hasRole('ADMIN','USER')")
	public void edit(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepository.save(employee);
	}
	@PreAuthorize(value="hasRole('ADMIN')")
	public void delete(int employeeId) {
		employeeRepository.delete(employeeId);

	}
	@PreAuthorize(value="hasRole('ADMIN')")
	public Employee getEmployee(int employeeId) {
		return (Employee)employeeRepository.findOne(employeeId);//.getEmployeeById(employeeId);
	}
	
	public Employee getEmployeeDetail(String username) {
		return (Employee)employeeRepository.getEmployeeByUsername(username);//.getEmployeeById(employeeId);
	}

	@PreAuthorize(value="hasRole('ADMIN')")
	public List<Employee> getAllEmployee() {
		return (List<Employee>)employeeRepository.findAll();
	}

	public boolean checkUsername(String username, int employeeId) 
	{
		Employee employee = employeeRepository.getEmployeeByUsername(username);
		return (employee!=null && employee.getId()!= employeeId);
	
	}
	
	public String encryptPass(String password) {
		BCryptPasswordEncoder pass1 = new BCryptPasswordEncoder();
		return pass1.encode(password);
	}

	public boolean checkPassword(String password, String encodedPassword) {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		return passEncoder.matches(password, encodedPassword);
	}

	@Override
	public Employee validateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Employee> getEmployeeByName(String name) {
	return employeeRepository.getEmployeeByName(name);
	}
	@Override
	public List<Employee> getScrumMasters() {
		return employeeRepository.getEmployeesByRole("SCRUM_MASTER");
	}
	@Override
	public List<Employee> getAllDevelopers() {
		return employeeRepository.getEmployeesByRole("DEVELOPER");
	}
	@Override
	public List<Employee> getAllTesters() {
		return employeeRepository.getEmployeesByRole("TESTER");
	}

}