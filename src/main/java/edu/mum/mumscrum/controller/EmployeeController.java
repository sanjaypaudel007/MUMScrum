package edu.mum.mumscrum.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.Role;
import edu.mum.mumscrum.response.ResponseStatusException;
import edu.mum.mumscrum.service.EmployeeService;
import edu.mum.mumscrum.service.RoleService;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService roleService;

	private List<Role> roleList;

	@ModelAttribute("roleList")
	public List<Role> getTeams() {
		return roleService.getAllList();
	}

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index(Model model) {
		List<Employee> employeeList = employeeService.getAllEmployee();
		model.addAttribute("employeeList", employeeList);
		return "employee/index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute("employee") Employee employee,
			Model model) {
		roleList = roleService.getAllList();
		model.addAttribute("roleList", roleList);
		return "employee/register";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Set.class, "roles",
				new CustomCollectionEditor(Set.class) {

					@Override
					protected Object convertElement(Object element) {
						Role role = new Role();

						if (element != null) {
							Integer id = Integer.valueOf(element.toString());
							role.setId(id);
						}
						return role;
					}
				});

	}

	//creates new employee
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String processRegistration(
			@Valid @ModelAttribute("employee") Employee employee,
			BindingResult result, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {

		if (result.hasErrors()) {
			return "employee/register";
		}

		if (employee.getPassword().equals(employee.getRePassword())) {
			if (!employeeService.checkUsername(employee.getUsername())) {
				employee.setPassword(employeeService.encryptPass(employee
						.getPassword()));
				// User Image
				MultipartFile employeeImage = employee.getImage();
				if (employeeImage != null && !employeeImage.isEmpty()) {
					String rootDictory = request.getSession()
							.getServletContext().getRealPath("/");
					String imageSaveName = String.valueOf(employee
							.getUsername())
							+ employeeImage.getOriginalFilename();
					employee.setImageUrl(imageSaveName);
					employeeImage.transferTo(new File(rootDictory
							+ "resources\\employeeImages\\" + imageSaveName));
				}
				// save
				employeeService.addEmployee(employee);
				return "redirect:/employee";
			} else {

				result.rejectValue("username", "error.username",
						"Try Again!! User Already exist!");
			}

		} else {

		}

		return "employee/register";
	}

	@RequestMapping(value = { "/detail/{id}" }, method = RequestMethod.GET)
	public String displayDetail(@PathVariable Integer id, ModelMap model) {
		Employee employee = employeeService.getEmployee(id);

		if (employee == null)
			throw new ResponseStatusException(
					"The requested user exist doesn't exist.");
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	//details
	@RequestMapping(value = { "/detail" }, method = RequestMethod.GET)
	public String displayDetailByName(ModelMap model) {
		Employee employee;

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		employee = employeeService.getEmployeeDetail(username);

		if (employee == null)
			throw new ResponseStatusException(
					"The requested user exist doesn't exist.");
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	//delete
	@RequestMapping(value = "/delete/{employeeId}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable int employeeId) {
		employeeService.deleteEmployee(employeeId);
		return "redirect:/employee";
	}

	@RequestMapping(value = "/edit/{employeeId}", method = RequestMethod.GET)
	public String editEmployee(@PathVariable int employeeId, ModelMap model) {
		Employee employee = employeeService.getEmployee(employeeId);
		List<Role> roleLists = roleService.getAllList();
		Set<Role> roleList = new HashSet<Role>(roleLists);
		model.addAttribute(employee);
		model.addAttribute("roleList", roleList);
		return "employee/edit";
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String editEmployee(ModelMap model) {
		Employee employee;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		employee = employeeService.getEmployeeDetail(username);
		List<Role> roleLists = roleService.getAllList();
		Set<Role> roleList = new HashSet<Role>(roleLists);
		model.addAttribute(employee);
		model.addAttribute("roleList", roleList);
		return "employee/editProfile";
	}

	//edit Profile	
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String editEmployee(
			@Valid @ModelAttribute("employee") Employee employee,
			BindingResult result, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {
		Employee employeeOld;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		employeeOld = employeeService.getEmployeeDetail(username);
		employee.setId(employeeOld.getId());
		employee.setPassword(employeeOld.getPassword());
		if (result.hasErrors()) {
			return "employee/editProfile";
		}
		MultipartFile employeeImage = employee.getImage();
		if (employeeImage != null && !employeeImage.isEmpty()) {
			String rootDictory = request.getSession().getServletContext()
					.getRealPath("/");
			String imageSaveName = String.valueOf(employee.getUsername())
					+ employeeImage.getOriginalFilename();
			employee.setImageUrl(imageSaveName);
			employeeImage.transferTo(new File(rootDictory
					+ "resources\\employeeImages\\" + imageSaveName));
		} else {
			employee.setImageUrl(employeeOld.getImageUrl());

		}
		employeeService.addEmployee(employee);
		return "redirect:/employee/detail";

	}

	//edit Employee
	@RequestMapping(value = "/edit/{employeeId}", method = RequestMethod.POST)
	public String editEmployee(@PathVariable int employeeId,
			@Valid @ModelAttribute("employee") Employee employee,
			BindingResult result, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {

		employee.setId(employeeId);
		Employee oldemployee = employeeService.getEmployee(employeeId);
		employee.setPassword(oldemployee.getPassword());
		if (result.hasErrors()) {
			return "employee/edit";
		}
		MultipartFile employeeImage = employee.getImage();
		if (employeeImage != null && !employeeImage.isEmpty()) {
			String rootDictory = request.getSession().getServletContext()
					.getRealPath("/");
			String imageSaveName = String.valueOf(employee.getUsername())
					+ employeeImage.getOriginalFilename();
			employee.setImageUrl(imageSaveName);
			employeeImage.transferTo(new File(rootDictory
					+ "resources\\employeeImages\\" + imageSaveName));
		} else {
			Employee employeeOld = employeeService.getEmployee(employeeId);
			employee.setImageUrl(employeeOld.getImageUrl());

		}
		employeeService.addEmployee(employee);
		return "redirect:/employee";

	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changePassword(ModelMap model) {
		return "employee/changepassword";
	}

	//change Password
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String changePassword(
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("password") String password,
			@RequestParam("rePassword") String rePassword, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		if (password.equals(rePassword)) {
			if (employeeService.changePassword(username, oldPassword, password)) {
				return "redirect:/";
			}
		}
		return "employee/changepassword";

	}

}
