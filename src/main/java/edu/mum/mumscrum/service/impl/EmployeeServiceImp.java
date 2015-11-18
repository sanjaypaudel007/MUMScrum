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

//	@PreAuthorize(value = "hasRole('ADMIN')")
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	public void deleteEmployee(int employeeId) {
		employeeRepository.delete(employeeId);
	}

	@PreAuthorize(value = "hasRole('ADMIN', 'USER')")
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	public Employee getEmployee(int employeeId) {
		return (Employee) employeeRepository.findOne(employeeId);
	}

	public Employee getEmployeeDetail(String username) {
		return (Employee) employeeRepository.getEmployeeByUsername(username);
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	public List<Employee> getAllEmployee() {
		return (List<Employee>) employeeRepository.findAll();
	}

	public boolean checkUsername(String username, int employeeId) {
		Employee employee = employeeRepository.getEmployeeByUsername(username);
		// return (employee != null && employee.getId() != employeeId);
		return (employee != null);

	}

	public String encryptPass(String password) {
		BCryptPasswordEncoder pass1 = new BCryptPasswordEncoder();
		return pass1.encode(password);
	}

	@Override
	public Employee validateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllDevelopers() {
		// return null;
		return employeeRepository.getEmployeesByRole("DEVELOPER");
	}

	@Override
	public List<Employee> getAllTesters() {
		// return null;
		return employeeRepository.getEmployeesByRole("TESTER");
	}

	@Override
	public boolean changePassword(String username, String oldPassword,
			String password) {
		Employee employee = employeeRepository.getEmployeeByUsername(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String savedPassword = employee.getPassword();

		if (passwordEncoder.matches(oldPassword, savedPassword)) {
			employee.setPassword(encryptPass(password));
			employeeRepository.save(employee);
			return true;
		} else {
			return false;
		}
	}

}
