package edu.mum.mumscrum.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.Role;
import edu.mum.mumscrum.response.Response;
import edu.mum.mumscrum.response.ResponseStatusException;
import edu.mum.mumscrum.service.EmployeeService;
import edu.mum.mumscrum.service.RoleService;
import edu.mum.mumscrum.validator.EmployeeFormValidator;




@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private EmployeeFormValidator employeeFormValidator;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index(Model model) {
		List<Employee> employeeList = employeeService.getAllEmployee();
		model.addAttribute("employeeList", employeeList);
		return "employee/index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute("userform") Employee employee, Model model) {
		List<Role> roleList = roleService.getAllList();
		model.addAttribute("roleList", roleList);
		return "employee/register";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String processRegistration(@Valid @ModelAttribute("userform") Employee employee, BindingResult result,
			Model model, HttpServletRequest request) throws IllegalStateException, IOException  {

		employeeFormValidator.setAction("Add");
		employeeFormValidator.validate(employee, result);
		
		List<Role> roleList = roleService.getAllList();
		model.addAttribute("roleList", roleList);
		
		if (result.hasErrors()) {
			return "employee/register";
		}
		if (employee.getPassword().equals(employee.getRePassword())) {
			if (!employeeService.checkUsername(employee.getUsername(), employee.getId())) {
				employee.setPassword(employeeService.encryptPass(employee.getPassword()));
				// User Image
				MultipartFile employeeImage = employee.getImage();
				if (employeeImage != null && !employeeImage.isEmpty()) {
					String rootDictory = request.getSession().getServletContext().getRealPath("/");
					String imageSaveName = String.valueOf(employee.getUsername()) + employeeImage.getOriginalFilename();
					employee.setImageUrl( imageSaveName );
					employeeImage.transferTo(new File(rootDictory+ "resources\\employeeImages\\"+imageSaveName));
				}
				//save
				employeeService.add(employee);
				return "redirect:/employee";
			} else {
				
//				result.rejectValue("username","error.username", "Try Again!! User Already exist!");
			}

		} else {
			
		}

		return "employee/register";
	}
	
	@RequestMapping(value = { "/detail/{id}"}, method = RequestMethod.GET)
	public String displayDetail(@PathVariable Integer id, ModelMap model) {
		Employee employee = employeeService.getEmployee(id);
		if (employee == null)
			throw new ResponseStatusException("The requested user exist doesn't exist.");
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") int employeeId, ModelMap model) {

		Employee employee = employeeService.getEmployee(employeeId);
		employee.setPassword("");
		
		List<Role> roleList = roleService.getAllList();
		model.addAttribute("roleList", roleList);
		model.addAttribute("employee", employee);
		return "employee/edit";
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.POST)
	public String update(@PathVariable("id") int employeeId, @Valid @ModelAttribute Employee employee, 
			BindingResult result,
			ModelMap model, HttpServletRequest request) throws IllegalStateException, IOException  {

		List<Role> roleList = roleService.getAllList();
		model.addAttribute("roleList", roleList);
		
		employee.setId(employeeId);
		employeeFormValidator.setAction("Edit");
		employeeFormValidator.validate(employee, result);
		
		if (employee.getEnablePasswordChange() == true) {
			if (employee.getPassword().equals(employee.getRePassword())) {
				employee.setPassword(employeeService.encryptPass(employee.getPassword()));
			} else {
				result.rejectValue("password", "Try Again!! Password and retype password not matched!");
			}
		}else
		{
			Employee oldemployee = employeeService.getEmployee(employeeId);
			employee.setPassword(oldemployee.getPassword());
			
		}
		if (result.hasErrors()) {
			return "employee/edit";
		}
		// User Image
		MultipartFile employeeImage = employee.getImage();
		if (employeeImage != null && !employeeImage.isEmpty()) {
			String rootDictory = request.getSession().getServletContext().getRealPath("/");
			String imageSaveName = String.valueOf(employee.getUsername()) + employeeImage.getOriginalFilename();
			employee.setImageUrl( imageSaveName );
			employeeImage.transferTo(new File(rootDictory+ "resources\\employeeImages\\"+imageSaveName));
		}else
		{
			Employee employeeOld = employeeService.getEmployee(employeeId);
			employee.setImageUrl(employeeOld.getImageUrl());
			
		}
		employeeService.add(employee);
		return "redirect:/employee";
		

	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Response delete(@PathVariable int id) throws RuntimeException
	{
		employeeService.delete(id);
		Response response = new Response();
		response.setMessage("Record successfully deleted.");
		response.setSuccess(true);
		return response;
	}
	
	@RequestMapping(value = { "/search/","/search" }, method = RequestMethod.GET)
	public List<Employee> search(@RequestParam("searchname") String searchname, HttpServletRequest request, Model model) {
//		return ("hhhhhhhhhhhhhhhhh"+request.getParameter("searchname")+"::::"+searchname);
		//List<Employee> employeeList = employeeService.getEmployeeByName(searchname);
		List<Employee> employeeList = employeeService.getAllEmployee();
		
		model.addAttribute("employeeList", employeeList);
		return employeeList;
	}

}
