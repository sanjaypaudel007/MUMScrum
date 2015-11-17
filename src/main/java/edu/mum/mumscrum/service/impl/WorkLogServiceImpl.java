package edu.mum.mumscrum.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.entity.UserStory;
import edu.mum.mumscrum.entity.WorkLog;
import edu.mum.mumscrum.repository.EmployeeRepository;
import edu.mum.mumscrum.repository.SprintRepository;
import edu.mum.mumscrum.repository.UserStoryRepository;
import edu.mum.mumscrum.repository.WorkLogRepository;
import edu.mum.mumscrum.response.ResponseStatusException;
import edu.mum.mumscrum.response.WorkLogFormModel;
import edu.mum.mumscrum.response.WorkLogFormModelEntry;
import edu.mum.mumscrum.service.WorkLogService;

@Service
public class WorkLogServiceImpl implements WorkLogService {

	@Autowired
	UserStoryRepository userStoryRepository;
	
	@Autowired
	WorkLogRepository workLogRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	SprintRepository sprintRepository;
	
	SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
	
	@Override
	public WorkLogFormModel getWorkLogFormData(String username) {
		Employee employee = employeeRepository.getEmployeeByUsername(username);
		List<UserStory> userStories = userStoryRepository.getUserStoriesForEmployee(employee);
		WorkLogFormModel model = new WorkLogFormModel();
		try {
			model.setToday(sf.parse(sf.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<WorkLogFormModelEntry> entries = new ArrayList<WorkLogFormModelEntry>();
		for(UserStory us: userStories){
			WorkLogFormModelEntry entry = new WorkLogFormModelEntry();
			entry.setUserStory(us);
			List<WorkLog> history = workLogRepository.getWorkLogForUserStoryAndEmployee(us, employee);
			WorkLog lastLog = getLastLog(history, model.getToday());
			if(lastLog != null){
				entry.setLastLogDate(lastLog.getDate());
				entry.setLastLogEstimation(lastLog.getRemainingWorkEstimation());
				entry.setNewEstimation(lastLog.getRemainingWorkEstimation());
			}
			WorkLog previousLogOfToday = getPreviousLogOfToday(history, model.getToday());
			if(previousLogOfToday != null){
				entry.setNewEstimation(previousLogOfToday.getRemainingWorkEstimation());
			}
			entries.add(entry);
		}
		model.setEntries(entries);
		return model;
	}

	private WorkLog getPreviousLogOfToday(List<WorkLog> history, Date today) {
		for(WorkLog log : history){
			if (sf.format(log.getDate()).equals(sf.format(today))){
				return log;
			}
		}
		return null;
	}

	private WorkLog getLastLog(List<WorkLog> logs, Date today) {
		WorkLog lastLog = null;
		for(WorkLog log: logs){
			if(log.getDate().before(today)){
				if(lastLog == null){
					lastLog = log;
				}
				else{
					if(log.getDate().after(lastLog.getDate())){
						lastLog = log;
					}
				}
			}
		}
		return lastLog;
	}

	@Override
	@Transactional
	public void add(WorkLogFormModel workFormData, String username) {
		Employee emp = employeeRepository.getEmployeeByUsername(username);
		for(WorkLogFormModelEntry entry: workFormData.getEntries()){
			UserStory userStory = userStoryRepository.findOne( entry.getUserStory().getId());
			workLogRepository.deleteExistingWorklog(userStory, emp, workFormData.getToday());
			WorkLog newLog = new WorkLog();
			newLog.setDate(workFormData.getToday());
			newLog.setUserStory(userStory);
			newLog.setEstimatingPerson(emp);
			newLog.setRemainingWorkEstimation(entry.getNewEstimation());
			workLogRepository.save(newLog);
		}
	}

	@Override
	public List<WorkLog> getAll(String username) {
		Employee employee = employeeRepository.getEmployeeByUsername(username);
		List<WorkLog> list = workLogRepository.getAllFor(employee);
		if(list == null){
			list = new ArrayList<WorkLog>();
		}
		return list;
	}

	@Transactional
	public Map<Date, Double> getBurndownData(Long sprintId) {
		Sprint sprint = sprintRepository.findOne(sprintId);
		Map<Date, Double> workLogs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
		  workLogs = sprint.generateBurndownChart(sdf.parse(sdf.format(new Date())));
		  if(workLogs != null && sprint != null && sprint.getEndDate() != null && !workLogs.containsKey(sprint.getEndDate())){
			  workLogs.put(sprint.getEndDate(), 0.0);
		  }
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ResponseStatusException(e.getMessage());
		}
		return workLogs;
	}

}
