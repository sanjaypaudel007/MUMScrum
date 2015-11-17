package edu.mum.mumscrum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.ReleaseBacklog;
import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.entity.UserStory;
import edu.mum.mumscrum.enums.Status;
import edu.mum.mumscrum.response.EstimationModel;
import edu.mum.mumscrum.response.Response;
import edu.mum.mumscrum.response.ResponseStatusException;
import edu.mum.mumscrum.service.EmployeeService;
import edu.mum.mumscrum.service.ReleaseBacklogService;
import edu.mum.mumscrum.service.UserStoryService;

@Controller
@RequestMapping(value = "/userstory")
public class UserStoryController {

	private static final Logger logger = Logger.getLogger(UserStoryController.class);
	@Autowired
	UserStoryService userStoryService;

	@Autowired
	ReleaseBacklogService releaseBacklogService;

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		String loggedInUsername = request.getUserPrincipal().getName();
		List<UserStory> list = userStoryService.getAllListFor(loggedInUsername);
		model.addAttribute("list", list);
		model.addAttribute("username", loggedInUsername);
		return "userstory/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getFormForAddUserStory(
			@RequestParam(value = "releasebacklog", required = false, defaultValue = "0") Long releaseBacklogId,
			Model model) {

		ReleaseBacklog releasebacklog = releaseBacklogService.getDetail(releaseBacklogId);
		List<ReleaseBacklog> listReleaseBackLog = releaseBacklogService.getAllList();

		UserStory userStory = new UserStory();
		userStory.setDevelopmentEstimate(0.0);
		userStory.setTestingEstimate(0.0);

		model.addAttribute("listReleaseBackLog", listReleaseBackLog);
		model.addAttribute("releasebacklog", releasebacklog);
		model.addAttribute("userStory", userStory);
		model.addAttribute("buttonName", "Save");
		model.addAttribute("title", "Add");
		return "userstory/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUserStory(@ModelAttribute("userStory") @Valid UserStory userStory, BindingResult br,
			@RequestParam(value = "releasebacklog", required = false, defaultValue = "0") Long releaseBacklogId,
			Model model) {

		ReleaseBacklog rb = releaseBacklogService.getDetail(releaseBacklogId);
		userStory.setStatus(Status.NEW);
		userStory.setReleaseBacklog(rb);

		logger.error(br.getAllErrors());
		if (br.hasErrors()) {
			model.addAttribute("buttonName", "Save");
			model.addAttribute("title", "Add");
			// model.addAttribute("userStory", userStory);
			return "userstory/add";
		}
		logger.info("Adding new release");
		userStoryService.add(userStory);
		return "redirect:/releasebacklog/detail/" + userStory.getReleaseBacklog().getId();
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") long userStoryId, ModelMap model) {

		UserStory userStory = userStoryService.getDetail(userStoryId);
		if (userStory == null)
			throw new ResponseStatusException();

		// List<ReleaseBacklog> listReleaseBackLog =
		// releaseBacklogService.getReleaseBacklogFor(productBacklogId);

		// model.addAttribute("listReleaseBackLog", listReleaseBackLog);
		model.addAttribute("userStory", userStory);
		model.addAttribute("buttonName", "Update");
		model.addAttribute("title", "Edit");
		return "userstory/add";

	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.POST)
	public String update(@PathVariable int id, @Valid @ModelAttribute UserStory userStory, BindingResult result,
			ModelMap model) {

		model.addAttribute("buttonName", "Update");
		model.addAttribute("title", "Edit");
		if (result.hasErrors()) {
			return "userstory/add";
		}
		userStoryService.edit(userStory.getId(), userStory.getName(), userStory.getDescription(),
				userStory.getDevelopmentEstimate());
		return "redirect:/userstory/detail/" + userStory.getId();

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	// @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody Response delete(@PathVariable long id, HttpServletRequest request) throws RuntimeException {
		// productBacklogService.delete(id);
		Response response = new Response();
		response.setMessage("Record successfully deleted.");
		response.setSuccess(true);
		return response;
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String displayUserstoryDetails(@PathVariable long id, ModelMap model) {

		UserStory userStory = userStoryService.getDetail(id);
		Employee developer = userStory.getDeveloper();
		List<Employee> developers = employeeService.getAllDevelopers();
		Employee tester = userStory.getTester();

		List<Employee> testers = employeeService.getAllTesters();

		if (userStory == null)
			throw new ResponseStatusException("The requested user exist doesn't exist.");

		model.addAttribute("userStory", userStory);
		model.addAttribute("developers", developers);
		model.addAttribute("testers", testers);
		return "userstory/detail";
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
	public String updateUserstoryDetails(@PathVariable("id") Long userStoryId,
			@ModelAttribute("userstory") UserStory userStory, ModelMap model) {
		userStoryService.updateUserStory(userStory);
		return "redirect:/userstory/list";
	}

	@RequestMapping(value = "/estimate/{id}", method = RequestMethod.GET)
	public String getEstimateForm(@PathVariable("id") Long userStoryId, Model model, HttpServletRequest request){
		String username = request.getUserPrincipal().getName();
		UserStory userStory = userStoryService.getDetail(userStoryId);
		model.addAttribute("userStory", userStory);
		return "userstory/estimate";
	}

	@RequestMapping(value = "/estimate/{id}", method = RequestMethod.POST)
	public String doEstimate(@PathVariable("id") Long userStoryId, Model model, @ModelAttribute("userStory") UserStory userStory, HttpServletRequest request){
		String username = request.getUserPrincipal().getName();
		userStoryService.updateEstimation(userStory, username);
		return "redirect:/userstory/";
	}
}
