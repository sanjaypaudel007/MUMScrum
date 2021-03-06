package edu.mum.mumscrum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.entity.UserStory;
import edu.mum.mumscrum.entity.WorkLog;
import edu.mum.mumscrum.enums.Status;
import edu.mum.mumscrum.repository.EmployeeRepository;
import edu.mum.mumscrum.repository.ReleaseBacklogRepository;
import edu.mum.mumscrum.repository.UserStoryRepository;
import edu.mum.mumscrum.repository.WorkLogRepository;
import edu.mum.mumscrum.service.UserStoryService;

@Service
public class UserStoryServiceImpl implements UserStoryService {
	
	@Autowired
	ReleaseBacklogRepository releaseBacklogRepository;

	@Autowired
	UserStoryRepository userStoryRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	WorkLogRepository worklogRepository;

//	@Override
//	public List<UserStory> getUserStoryFor(Long releaseBacklogId) {
//		ReleaseBacklog pb = ReleaseBacklogRepository.findOne(releaseBacklogId);
//		List<UserStory> list = userStoryRepository.getForUserStory(pb);
//		return list;
//	}

	@Override
	public void add(UserStory userStory) {
		userStoryRepository.save(userStory);

	}

	@Override
	public UserStory getDetail(long userStoryId) {
		return userStoryRepository.findOne(userStoryId);
	}

	@Override
	public void edit(Long userStoryId, UserStory userStory) {

		UserStory rb = userStoryRepository.findOne(userStoryId);
		userStory.setReleaseBacklog(rb.getReleaseBacklog());
		userStoryRepository.save(userStory);

	}
	
	@Override
	public void edit(Long userStoryId, String name, String description, Double developmentEstimate)
	{
		UserStory entity = userStoryRepository.findOne(userStoryId);
		entity.setName(name);
		entity.setDescription(description);
		entity.setDevelopmentEstimate(developmentEstimate);
		userStoryRepository.save(entity);
	}

	@Override
	public List<UserStory> getAllListFor(String loggedInUsername) {
		List<UserStory> list = (List<UserStory>) userStoryRepository.findAll();
		return list;
	}

	@Override
	@Transactional
	@PreAuthorize(value = "hasRole('SCRUM_MASTER')")
	public void updateUserStory(UserStory userStory) {
		Employee developer = employeeRepository.findOne(userStory.getDeveloper().getId());
		Employee tester = employeeRepository.findOne(userStory.getTester().getId());
		userStory = userStoryRepository.findOne(userStory.getId());
		userStory.setDeveloper(developer);
		userStory.setTester(tester);
		userStory.setStatus(Status.ASSIGNED);
		userStoryRepository.save(userStory);
		
	}

	@Override
	@Transactional
	public void updateEstimation(UserStory us, String username) {
		Employee user = employeeRepository.getEmployeeByUsername(username);
		UserStory userStory = userStoryRepository.findOne(us.getId());
		userStory.setStatus(Status.ESTIMATED);
		WorkLog wl = new WorkLog();
		wl.setEstimatingPerson(user);
		
		if (userStory.getDeveloper().getId() == user.getId()) {
			userStory.setDevelopmentEstimate(us.getDevelopmentEstimate());
			wl.setRemainingWorkEstimation(us.getDevelopmentEstimate());
		} else {
			userStory.setTestingEstimate(us.getTestingEstimate());
			wl.setRemainingWorkEstimation(us.getTestingEstimate());
		}

		wl.setUserStory(userStory);
		worklogRepository.deleteExistingWorklog(wl.getUserStory(), wl.getEstimatingPerson(), wl.getDate());
		worklogRepository.save(wl);
		userStoryRepository.save(userStory);
	}
}
