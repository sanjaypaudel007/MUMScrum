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
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.Pair;
import edu.mum.mumscrum.entity.ReleaseBacklog;
import edu.mum.mumscrum.enums.Status;
import edu.mum.mumscrum.response.ResponseStatusException;
import edu.mum.mumscrum.service.EmployeeService;
import edu.mum.mumscrum.service.ReleaseBacklogService;
import edu.mum.mumscrum.service.SprintService;

@Controller
@RequestMapping(value="/releasebacklog")
public class ReleaseBacklogController {
	
	@Autowired
	ReleaseBacklogService releaseBacklogService;
	
//	@Autowired
//	ProductBacklogService productBacklogService;
	
	@Autowired
	SprintService sprintService;
	
	@Autowired
	EmployeeService employeeService;
	
	private static final Logger logger = Logger.getLogger(ReleaseBacklogController.class);
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public List<ReleaseBacklog> getAllReleaseBacklogs(Model model, HttpServletRequest request){
		String loggedinUsername = request.getUserPrincipal().getName();
		logger.info("Listing releases for the user " + loggedinUsername);
		
		List<ReleaseBacklog> list = releaseBacklogService.getReleaseBacklogForUser(loggedinUsername);
		model.addAttribute("list", list);
		return list;
	}
	
	@RequestMapping(value="/add", method= RequestMethod.GET)
	public String getFormForAddReleaseBacklog(Model model, HttpServletRequest request){
//		ProductBacklog pb = productBacklogService.getDetail(productBacklog);
		ReleaseBacklog rb = new ReleaseBacklog();
//		rb.setProductBacklog(pb);
		model.addAttribute("releaseBacklog", rb);
		model.addAttribute("buttonName", "Save");
		model.addAttribute("title", "Add");
		return "releasebacklog/add";
	}
	
	@RequestMapping(value="/add", method= RequestMethod.POST)
	public String addReleaseBacklog(@ModelAttribute("releaseBacklog") @Valid ReleaseBacklog releaseBacklog, BindingResult br, Model model, HttpServletRequest request){
		if(br.hasErrors()){
			model.addAttribute("buttonName", "Save");
			model.addAttribute("title", "Add");
			return "releasebacklog/add";
		}
		logger.info("Adding new release");
		Employee scrumMaster = new Employee();
		scrumMaster.setId((int) request.getSession().getAttribute("loginId"));
		releaseBacklog.setScrumMaster(scrumMaster);
		releaseBacklog.setStatus(Status.ASSIGNED);
		releaseBacklogService.add(releaseBacklog);
		return "redirect:/releasebacklog/detail/" + releaseBacklog.getId();
	}
	
	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long releaseBacklogId, ModelMap model) {
		logger.info("Editing release backlog with id " + releaseBacklogId);
		ReleaseBacklog releaseBacklog = releaseBacklogService.getDetail(releaseBacklogId);
		if (releaseBacklog == null)
			throw new ResponseStatusException("Requested release backlog doesn't exist");
		model.addAttribute("buttonName", "Update");
		model.addAttribute("title", "Edit");
		model.addAttribute("releaseBacklog", releaseBacklog);
		logger.info("test");
		return "releasebacklog/add";
		
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.POST)
	public String update(@PathVariable("id") Long releaseBacklogId, @Valid @ModelAttribute("releaseBacklog") ReleaseBacklog releaseBacklog, BindingResult result,
			Model model) {
		logger.info("Submitted");
		if (result.hasErrors()) {
			logger.info("Error");
			model.addAttribute("buttonName", "Update");
			model.addAttribute("title", "Edit");
			return "releasebacklog/add";
		}
		logger.info("Success");
		releaseBacklogService.edit(releaseBacklogId, releaseBacklog);
		return "redirect:/releasebacklog/detail/"+releaseBacklog.getId();

	}
	
	/*
	@RequestMapping(value = { "/startwork/{id}" }, method = RequestMethod.GET)
	public String getStartWorkingForm(@PathVariable("id") Long releaseBacklogId, ModelMap model) {
		logger.info("Editing release backlog with id " + releaseBacklogId);
		ReleaseBacklog releaseBacklog = releaseBacklogService.getDetail(releaseBacklogId);
		if (releaseBacklog == null)
			throw new ResponseStatusException("Requested release backlog doesn't exist");
		if(releaseBacklog.getScrumMaster() == null){
			releaseBacklog.setScrumMaster(new Employee());
		}
		List<Employee> scrumMasters = employeeService.getScrumMasters();
		model.addAttribute("scrumMasters", scrumMasters);
		model.addAttribute("releaseBacklog", releaseBacklog);
		logger.info("Starting the release backlog.");
		return "releasebacklog/startworking";		
	}
	
	@RequestMapping(value = { "/startwork/{id}" }, method = RequestMethod.POST)
	public String doStartWork(@PathVariable("id") Long releaseBacklogId, @ModelAttribute("releaseBacklog") ReleaseBacklog rb, ModelMap model) {
		logger.info("Editing release backlog with id " + releaseBacklogId);		
		Integer scrumMasterId = rb.getScrumMaster().getId();
		releaseBacklogService.setScrumMaster(rb, scrumMasterId);
		return "redirect:/releasebacklog/detail/"+ releaseBacklogId;		
	}
	*/
	
	@RequestMapping(value="/detail/{id}")
	public String getDetail(@PathVariable("id") Long releaseBacklogId, Model model){
		ReleaseBacklog rb = releaseBacklogService.getDetailWithUserStories(releaseBacklogId);
		model.addAttribute("releaseBacklog", rb);
		model.addAttribute("addedUserStories", rb.getUserStories());
//		model.addAttribute("notAddedUserStories", productBacklogService.getUserStoriesNotAddedToReleaseBacklog(rb.getProductBacklog()));
		model.addAttribute("sprints", sprintService.getSprintFor(releaseBacklogId));
		return "releasebacklog/detail";
	}
	
	/*
	@RequestMapping(value = "/optionlist/{id}", method = RequestMethod.GET)
	// @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody List< Pair<Integer, String> > optionList(@PathVariable long id, HttpServletRequest request) throws RuntimeException {
		return releaseBacklogService.getOptionList(id);
	}
	
	@RequestMapping(value = "/{rb_id}/addus/{us_id}")
	public String addUserStory(@PathVariable("rb_id") Long releaseBacklogId, @PathVariable("us_id") Long userStoryId){
		releaseBacklogService.addUserStory(releaseBacklogId, userStoryId);
		return "redirect:/releasebacklog/detail/" + releaseBacklogId;
	}
	
	@RequestMapping(value = "/{rb_id}/removeus/{us_id}")
	public String removeUserStory(@PathVariable("rb_id") Long releaseBacklogId, @PathVariable("us_id") Long userStoryId){
		releaseBacklogService.removeUserStory(releaseBacklogId, userStoryId);
		return "redirect:/releasebacklog/detail/" + releaseBacklogId;
	}
	*/
}
