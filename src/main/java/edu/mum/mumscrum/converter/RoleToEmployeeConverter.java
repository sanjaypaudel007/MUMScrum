package edu.mum.mumscrum.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.service.EmployeeService;

@Component
public class RoleToEmployeeConverter implements Converter<Object, Employee> {
	@Autowired
	EmployeeService employeeService;
	
	@Override
	public Employee convert(Object element) {
		Integer employeeId = Integer.parseInt((String)element);
        Employee employee= employeeService.getEmployee(employeeId);
        System.out.println("Profile : "+ employee);
        return employee;
	}
}
