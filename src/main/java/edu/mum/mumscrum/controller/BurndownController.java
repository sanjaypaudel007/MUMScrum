package edu.mum.mumscrum.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.repository.SprintRepository;
import edu.mum.mumscrum.service.WorkLogService;


@Controller
@RequestMapping(value = "/burndown")
public class BurndownController {

	@Autowired
	SprintRepository sprintRepository;
	@Autowired
	WorkLogService workLogService;

	@RequestMapping(value = "/sprints", method = RequestMethod.GET)
	public String listSprints(Model model) {
		List<Sprint> list = (List<Sprint>) sprintRepository.findAll();
		List<Sprint> result = new ArrayList<Sprint>();
		for (Sprint s : list) {
			result.add(s);
		}
		model.addAttribute("list", result);
		return "sprint/list";
	}

	@RequestMapping(value = "/sprint/{sprintId}")
	public String generateBurndownChart(@PathVariable("sprintId") Long sprintId, Model model) {
		
		Map<Date, Double> workLogs = workLogService.getBurndownData(sprintId);
		model.addAttribute("data", workLogs);
		return "worklog/chart";
	}

}
