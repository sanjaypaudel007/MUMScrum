
package edu.mum.mumscrum.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.Pair;
import edu.mum.mumscrum.entity.ReleaseBacklog;
import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.entity.UserStory;
import edu.mum.mumscrum.entity.WorkLog;
import edu.mum.mumscrum.enums.Status;
import edu.mum.mumscrum.repository.EmployeeRepository;
import edu.mum.mumscrum.repository.ReleaseBacklogRepository;
import edu.mum.mumscrum.repository.SprintRepository;
import edu.mum.mumscrum.repository.UserStoryRepository;
import edu.mum.mumscrum.repository.WorkLogRepository;
import edu.mum.mumscrum.response.ResponseStatusException;
import edu.mum.mumscrum.service.SprintService;



@Service
public class SprintServiceImpl implements SprintService {

	@Autowired
	SprintRepository sprintRepository;
	@Autowired
	ReleaseBacklogRepository releaseBacklogRepository;
	@Autowired
	UserStoryRepository userStoryRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	WorkLogRepository workLogRepository;

	@Override
	public List<Sprint> getSprintFor(Long releaseBacklogId) {
		ReleaseBacklog rb = releaseBacklogRepository.findOne(releaseBacklogId);
		List<Sprint> list = sprintRepository.getForReleaseBacklog(rb);
		return list;
	}

	@Override
	public void add(Sprint sprint, Long releaseBacklogId) {
		ReleaseBacklog rb = releaseBacklogRepository.findOne(releaseBacklogId);
		sprint.setReleaseBacklog(rb);
		sprint.setStatus(Status.NEW);
		sprintRepository.save(sprint);
	}

	@Override
	public Sprint getDetail(long sprintId) {
		return sprintRepository.findOne(sprintId);
	}

	@Override
	public void edit(Long sprintId, Sprint sprint) {
		Sprint sp = sprintRepository.findOne(sprintId);
		sprint.setReleaseBacklog(sp.getReleaseBacklog());
		sprintRepository.save(sprint);
	}

	@Override
	public List<Sprint> getAllList() {
		// TODO Auto-generated method stub
		return (List<Sprint>) sprintRepository.findAll();
	}

	@Override
	public List<Pair<Integer, String>> getOptionList(Long releaseBacklogId) {
		List< Pair<Integer, String>> optionList = new ArrayList< Pair<Integer,String>>();
		ReleaseBacklog releaseBacklog = releaseBacklogRepository.findOne(releaseBacklogId);
		List<Sprint> sprintList = sprintRepository.getForReleaseBacklog(releaseBacklog);
		
		if(sprintList != null){
			for(Sprint sprint: sprintList){
				optionList.add(new Pair(sprint.getId(), sprint.getName()));
			}
		}
		return optionList;
	}

	@Override
	@Transactional
	public Sprint getDetailWithUserStories(Long sprintId) {
		Sprint sp = sprintRepository.findOne(sprintId);
		List<UserStory> userStories = new ArrayList<UserStory>();
		for(UserStory us: sp.getUserStories()){
			userStories.add(us);
		}
		sp.setUserStories(userStories);
		return sp;
		
	}

	@Override
	@Transactional
	public void addUserStory(Long sprintId, Long userStoryId) {
		Sprint sp = sprintRepository.findOne(sprintId);
		UserStory us = userStoryRepository.findOne(userStoryId);
		if(sp == null){
			throw new ResponseStatusException("The requested sprint doesn't exist");
		}
		if(us == null){
			throw new ResponseStatusException("The requested user story doesn't exist");
		}
		sp.getUserStories().add(us);
		us.setSprint(sp);
		us.setStatus(Status.IN_PROGRESS);
		updateEstimation(sp, us);
		sprintRepository.save(sp);
		
	}

	@Override
	@Transactional
	public void removeUserStory(Long sprintId, Long userStoryId) {
		Sprint sp = sprintRepository.findOne(sprintId);
		UserStory us = userStoryRepository.findOne(userStoryId);
		if(sp == null){
			throw new ResponseStatusException("The requested sprint doesn't exist");
		}
		if(us == null){
			throw new ResponseStatusException("The requested user story doesn't exist");
		}
		sp.getUserStories().remove(us);
		us.setSprint(null);
		us.setStatus(Status.ESTIMATED);
		sprintRepository.save(sp);
		
	}

	@Override
	@Transactional
	@PreAuthorize(value = "hasRole('SCRUM_MASTER')")
	public void startSprint(Sprint s) {
		Sprint sprint = sprintRepository.findOne(s.getId());
//		sprint.setStartDate(s.getStartDate());
//		sprint.setEndDate(s.getEndDate());
		sprint.setStatus(Status.IN_PROGRESS);
		sprintRepository.save(sprint);
//		for(UserStory us: s.getUserStories()){
//			UserStory userStory = userStoryRepository.findOne(us.getId());
//			Employee developer = employeeRepository.findOne(us.getDeveloper().getId());
//			userStory.setDeveloper(developer);
//			Employee tester = employeeRepository.findOne(us.getTester().getId());
//			userStory.setTester(tester);
//			userStory.setStatus(Status.ASSIGNED);
//			userStoryRepository.save(userStory);
//		}
	}

	@Override
	public List<UserStory> getSprintUserStoriesForUser(Long sprintId, String username) {
		Employee developerOrTester = employeeRepository.getEmployeeByUsername(username);
		Sprint sprint = sprintRepository.findOne(sprintId);
		List<UserStory> result = userStoryRepository.getSprintUserStoriesForEstimation(sprint, developerOrTester, Status.ASSIGNED);
		return result;
	}

//	@Override
//	@Transactional
//	public void updateEstimation(Sprint sprint, List<UserStory> userStories, String username) {
//		Employee user = employeeRepository.getEmployeeByUsername(username);
//		for(UserStory us: userStories){
//			UserStory userStory = userStoryRepository.findOne(us.getId());
//			userStory.setStatus(Status.IN_PROGRESS);
//			if(userStory.getDeveloper().getId() == user.getId()){
//				userStory.setDevelopmentEstimate(us.getDevelopmentEstimate());
//			}else{
//				userStory.setTestingEstimate(us.getDevelopmentEstimate());
//			}
//			WorkLog wl = new WorkLog();
//			wl.setDate(sprint.getStartDate());
//			wl.setEstimatingPerson(user);
//			wl.setRemainingWorkEstimation(us.getDevelopmentEstimate());
//			wl.setUserStory(userStory);
//			workLogRepository.deleteExistingWorklog(wl.getUserStory(), wl.getEstimatingPerson(), wl.getDate());
//			workLogRepository.save(wl);
//			userStoryRepository.save(userStory);
//		}
//	}
	
	public void updateEstimation(Sprint sprint, UserStory userStory) {
		Iterable<WorkLog> workLog = workLogRepository.findAll(userStory);
		for (WorkLog wl : workLog) {
			wl.setDate(sprint.getStartDate());
			workLogRepository.save(wl);
		}
	}
	
	@Override
	@PreAuthorize(value = "hasRole('SCRUM_MASTER')")
	public void delete(Long id) {
		// TODO Auto-generated method stub
		sprintRepository.delete(id);
	}
}
