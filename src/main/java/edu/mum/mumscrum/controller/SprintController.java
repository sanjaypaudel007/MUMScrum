
package edu.mum.mumscrum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.Pair;
import edu.mum.mumscrum.entity.ReleaseBacklog;
import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.entity.UserStory;
import edu.mum.mumscrum.response.EstimationModel;
import edu.mum.mumscrum.response.Response;
import edu.mum.mumscrum.response.ResponseStatusException;
import edu.mum.mumscrum.service.EmployeeService;
import edu.mum.mumscrum.service.ReleaseBacklogService;
import edu.mum.mumscrum.service.SprintService;

@Controller
@RequestMapping(value = "/sprint")
public class SprintController {

	@Autowired
	SprintService sprintService;

	@Autowired
	ReleaseBacklogService releaseBacklogService;
	
	@Autowired
	EmployeeService employeeService;

	private static final Logger logger = Logger.getLogger(SprintController.class);

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String getAllReleaseBacklogsFor(Model model) {
		logger.info("Listing sprints ");
		List<Sprint> list = sprintService.getAllList();
		model.addAttribute("list", list);
		return "sprint/list";
	}

	@RequestMapping(value = "/add/{rb_id}", method = RequestMethod.GET)
	public String getFormForCreateSprint(@PathVariable("rb_id") Long releaseBacklog, Model model) {
		ReleaseBacklog rb = releaseBacklogService.getDetail(releaseBacklog);
		Sprint sp = new Sprint();
		sp.setReleaseBacklog(rb);
		model.addAttribute("sprint", sp);
		model.addAttribute("buttonName", "Add");
		model.addAttribute("title", "Add");
		return "sprint/add";
	}

	@RequestMapping(value = "/add/{rb_id}", method = RequestMethod.POST)
	public String add(@PathVariable("rb_id") Long releaseBacklogId, @ModelAttribute("sprint") @Valid Sprint sprint,
			BindingResult br, Model model) {
		if (br.hasErrors()) {
			logger.info("Error on adding new sprint");
			model.addAttribute("buttonName", "Add");
			model.addAttribute("title", "Add");
			return "sprint/add";
		}
		logger.info("Adding new sprint");
		sprintService.add(sprint, releaseBacklogId);
		return "redirect:/sprint/detail/" + sprint.getId();
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long sprintId, ModelMap model) {
		logger.info("Editing sprint with id " + sprintId);
		Sprint sprint = sprintService.getDetail(sprintId);
		if (sprint == null)
			throw new ResponseStatusException();
		model.addAttribute("buttonName", "Update");
		model.addAttribute("title", "Edit");
		model.addAttribute("sprint", sprint);
		logger.info("test");
		return "sprint/add";
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.POST)
	public String update(@PathVariable("id") Long sprintId, @Valid @ModelAttribute("sprint") Sprint sprint,
			BindingResult result, Model model) {
		logger.info("Submitted");
		if (result.hasErrors()) {
			logger.info("Error");
			model.addAttribute("buttonName", "Update");
			model.addAttribute("title", "Edit");
			return "sprint/add";
		}
		logger.info("Success");
		sprintService.edit(sprintId, sprint);
		return "redirect:/sprint/detail/" + sprint.getId();

	}

	@RequestMapping(value = "/detail/{id}")
	public String getDetail(@PathVariable("id") Long sprintId, Model model) {
		Sprint sp = sprintService.getDetailWithUserStories(sprintId);
		model.addAttribute("sprint", sp);
		model.addAttribute("addedUserStories", sp.getUserStories());
		model.addAttribute("notAddedUserStories",
				releaseBacklogService.getUserStoriesNotAddedToSprint(sp.getReleaseBacklog()));
		model.addAttribute("sprints", sprintService.getSprintFor(sprintId));
		return "sprint/detail";
	}

	@RequestMapping(value = "/optionlist/{id}", method = RequestMethod.GET)
	// @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody List<Pair<Integer, String>> optionList(@PathVariable long id, HttpServletRequest request)
			throws RuntimeException {
		return sprintService.getOptionList(id);
	}

	@RequestMapping(value = "/{rb_id}/addus/{us_id}")
	public String addUserStory(@PathVariable("rb_id") Long sprintId, @PathVariable("us_id") Long userStoryId) {
		sprintService.addUserStory(sprintId, userStoryId);
		return "redirect:/sprint/detail/" + sprintId;
	}

	@RequestMapping(value = "/{rb_id}/removeus/{us_id}")
	public String removeUserStory(@PathVariable("rb_id") Long sprintId, @PathVariable("us_id") Long userStoryId) {
		sprintService.removeUserStory(sprintId, userStoryId);
		return "redirect:/sprint/detail/" + sprintId;
	}
	
	@RequestMapping(value = { "/startwork/{id}" }, method = RequestMethod.GET)
	public String getStartWorkingForm(@PathVariable("id") Long sprintId, ModelMap model) {
		logger.info("Editing release backlog with id " + sprintId);
		Sprint sprint = sprintService.getDetailWithUserStories(sprintId);
		sprintService.startSprint(sprint);
		return "redirect:/sprint/detail/"+ sprintId;
//		if (sprint == null)
//			throw new ResponseStatusException("Requested sprint doesn't exist");
//		for(UserStory us: sprint.getUserStories()){
//			if(us.getDeveloper() == null) us.setDeveloper(new Employee());
//			if(us.getTester() == null ) us.setTester(new Employee());
//		}
//		List<Employee> developers = employeeService.getAllDevelopers();
//		List<Employee> testers = employeeService.getAllTesters();
//		model.addAttribute("developers", developers);
//		model.addAttribute("testers", testers);
//		model.addAttribute("sprint", sprint);
//		logger.info("Starting the sprint.");
//		return "sprint/startworking";		
	}
	
//	@RequestMapping(value = { "/startwork/{id}" }, method = RequestMethod.POST)
//	public String doStartWork(@PathVariable("id") Long sprintId, @ModelAttribute("sprint") Sprint sprint, ModelMap model) {
//		logger.info("Editing release backlog with id " + sprintId);
//		sprintService.startSprint(sprint);
//		return "redirect:/sprint/detail/"+ sprintId;		
//	}
	
//	@RequestMapping(value = "/estimate/{id}", method = RequestMethod.GET)
//	public String getEstimateForm(@PathVariable("id") Long sprintId, Model model, HttpServletRequest request){
//		String username = request.getUserPrincipal().getName();
//		Sprint sprint = sprintService.getDetail(sprintId);
//		List<UserStory> userStories = sprintService.getSprintUserStoriesForUser(sprintId, username);
//		EstimationModel eModel = new EstimationModel();
//		eModel.setSprint(sprint);
//		eModel.setUserStories(userStories);
//		model.addAttribute("model", eModel);
//		return "sprint/estimate";
//	}
//
//	@RequestMapping(value = "/estimate/{id}", method = RequestMethod.POST)
//	public String doEstimate(@PathVariable("id") Long sprintId, Model model, @ModelAttribute("model") EstimationModel eModel, HttpServletRequest request){
//		String username = request.getUserPrincipal().getName();
//		Sprint sprint = sprintService.getDetail(sprintId);
//		sprintService.updateEstimation(sprint, eModel.getUserStories(), username);
//		return "redirect:/sprint/";
//	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Response delete(@PathVariable long id, HttpServletRequest request) throws RuntimeException {
		sprintService.delete(id);
		Response response = new Response();
		response.setMessage("Record successfully deleted.");
		response.setSuccess(true);
		return response;
	}
}
