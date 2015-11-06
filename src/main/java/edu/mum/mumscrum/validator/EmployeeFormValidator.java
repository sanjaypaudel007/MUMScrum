package edu.mum.mumscrum.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.service.EmployeeService;


enum Action {
    Add, Edit
}
@Component
public class EmployeeFormValidator implements Validator {
	@Autowired
	private EmployeeService employeeService;
 
	private String action;
	/*
	 * action could be Add or Edit
	 */
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		Employee employee = (Employee) target;
		if (employeeService.checkUsername(employee.getUsername(), employee.getId())) {
			errors.rejectValue("username","error.username", "Try Again!! User Already exist!!!");
		}
		switch (action) {
		case "Edit":
			
			if (employee.getEnablePasswordChange() == true) {
				System.out.println("Errorss ::"+employee.getPassword().equals(employee.getRePassword()));
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.employee.password");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rePassword", "error.employee.repassword");
				if (!employee.getPassword().equals(employee.getRePassword()) ) {
					//employee.setPassword(employeeService.encryptPass(employee.getPassword()));
					errors.rejectValue("password","error.password", "Try Again!! Password and retype password not matched!");
				}
			}else
			{
				Employee oldemployee = employeeService.getEmployee(employee.getId());
				employee.setPassword(oldemployee.getPassword());
				
			}
		
			break;
		case "Add":
		default:
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.employee.password");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rePassword", "error.employee.repassword");
			if (!employee.getPassword().equals(employee.getRePassword())) {
				errors.rejectValue("password","error.password", "Try Again!! Password and retype password not matched!");
			}
			break;
		}
		
	}

}
