package edu.mum.mumscrum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.mumscrum.entity.WorkLog;
import edu.mum.mumscrum.response.WorkLogFormModel;
import edu.mum.mumscrum.service.WorkLogService;

@Controller
@RequestMapping(value="/worklog")
public class WorkLogController {
	
	@Autowired
	WorkLogService workLogService ;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String getWorklogForm(Model model, @RequestParam(value = "message", defaultValue= "", required=false) String message, HttpServletRequest request){
		String username = request.getUserPrincipal().getName();
		WorkLogFormModel workFormData = workLogService.getWorkLogFormData(username);
		List<WorkLog> workLogs = workLogService.getAll(username);		
		model.addAttribute("userStoryEstimates", workFormData);
		model.addAttribute("list", workLogs);
		model.addAttribute("message", message);
		return "worklog/add";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String postWorklogForm(@ModelAttribute("userStoryEstimates") WorkLogFormModel workFormData, Model model, HttpServletRequest request){
		String username = request.getUserPrincipal().getName();
		workLogService.add(workFormData, username);
		model.addAttribute("message", "Saved successfully!!");
		return "redirect:/worklog/add";
	}
	
//
//	@RequestMapping(value="/list", method=RequestMethod.GET)
//	public String getWorklogList(Model model){
//		Integer employeeId = 3;
//		List<WorkLog> workLogs = workLogService.getAll(employeeId);
//		model.addAttribute("list", workLogs);
//		return "worklog/list";
//	}
}
