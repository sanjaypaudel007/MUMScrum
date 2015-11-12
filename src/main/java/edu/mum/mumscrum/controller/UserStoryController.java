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

import edu.mum.mumscrum.entity.UserStory;
import edu.mum.mumscrum.response.Response;
import edu.mum.mumscrum.response.ResponseStatusException;
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

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		String loggedInUsername = request.getUserPrincipal().getName();
		List<UserStory> list = userStoryService.getAllListFor(loggedInUsername);
		model.addAttribute("list", list);
		return "userstory/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getFormForAddUserStory(
			@RequestParam(value = "productbacklog", required = false, defaultValue = "0") Long productBacklogId,
			Model model) {
//
//		ProductBacklog productBacklog = productBacklogService.getDetail(productBacklogId);
//
//		List<ProductBacklog> listProductBackLog = productBacklogService.getAllList();
//		List<ReleaseBacklog> listReleaseBackLog = releaseBacklogService.getReleaseBacklogFor(productBacklogId);
//
//		UserStory userStory = new UserStory();
//		userStory.setProductBacklog(productBacklog);
//		userStory.setDevelopmentEstimate(0.0);
//		userStory.setTestingEstimate(0.0);
//
//		model.addAttribute("listProductBackLog", listProductBackLog);
//		model.addAttribute("listReleaseBackLog", listReleaseBackLog);
//		model.addAttribute("userStory", userStory);
//		model.addAttribute("buttonName", "Save");
//		model.addAttribute("title", "Add");
		return "userstory/add";
//
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUserStory(@ModelAttribute("userStory") @Valid UserStory userStory, BindingResult br,
			@RequestParam(value = "productbacklog", required = false, defaultValue = "0") Long productBacklogId,
			Model model) {

//		productBacklogId = userStory.getProductBacklog().getId();
//		ProductBacklog productBacklog = productBacklogService.getDetail(productBacklogId);
//
//		List<ProductBacklog> listProductBackLog = productBacklogService.getAllList();
//		List<ReleaseBacklog> listReleaseBackLog = releaseBacklogService.getReleaseBacklogFor(productBacklogId);
//
//		userStory.setProductBacklog(productBacklog);
//		userStory.setStatus(Status.NEW);
//
//		model.addAttribute("listProductBackLog", listProductBackLog);
//		model.addAttribute("listReleaseBackLog", listReleaseBackLog);
//		logger.error(br.getAllErrors());
//		if (br.hasErrors()) {
//			model.addAttribute("buttonName", "Save");
//			model.addAttribute("title", "Add");
//			//model.addAttribute("userStory", userStory);
			return "userstory/add";
//		}
//		logger.info("Adding new release");
//		userStoryService.add(userStory, productBacklogId);
//		return "redirect:/productbacklog/detail/" + userStory.getProductBacklog().getId();
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") long userStoryId, ModelMap model) {

//		UserStory userStory = userStoryService.getDetail(userStoryId);
//		if (userStory == null)
//			throw new ResponseStatusException();
//
//		List<ProductBacklog> listProductBackLog = productBacklogService.getAllList();
//		// List<ReleaseBacklog> listReleaseBackLog =
//		// releaseBacklogService.getReleaseBacklogFor(productBacklogId);
//
//		model.addAttribute("listProductBackLog", listProductBackLog);
//		// model.addAttribute("listReleaseBackLog", listReleaseBackLog);
//		model.addAttribute("userStory", userStory);
//		model.addAttribute("buttonName", "Update");
//		model.addAttribute("title", "Edit");
		return "userstory/add";

	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.POST)
	public String update(@PathVariable int id, @Valid @ModelAttribute UserStory userStory, BindingResult result,
			ModelMap model) {

//		model.addAttribute("buttonName", "Update");
//		model.addAttribute("title", "Edit");
//		if (result.hasErrors()) {
//			return "userstory/add";
//		}
//		userStoryService.edit(userStory.getId(), userStory.getName(), userStory.getDescription(),
//				userStory.getDevelopmentEstimate());
		return "redirect:/productbacklog/detail/" + userStory.getId();

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	// @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody Response delete(@PathVariable long id, HttpServletRequest request) throws RuntimeException {
//		productBacklogService.delete(id);
		Response response = new Response();
		response.setMessage("Record successfully deleted.");
		response.setSuccess(true);
		return response;
	}

	@RequestMapping(value = "/detail/{id}")
	public String displayProductBacklog(@PathVariable long id, ModelMap model) {

		UserStory userStory = userStoryService.getDetail(id);
		if (userStory == null)
			throw new ResponseStatusException("The requested user exist doesn't exist.");
		model.addAttribute("userStory", userStory);
		return "userstory/detail";
	}

}
