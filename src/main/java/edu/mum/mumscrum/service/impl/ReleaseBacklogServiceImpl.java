package edu.mum.mumscrum.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.ReleaseBacklog;
import edu.mum.mumscrum.entity.UserStory;
import edu.mum.mumscrum.enums.Status;
import edu.mum.mumscrum.repository.EmployeeRepository;
import edu.mum.mumscrum.repository.ReleaseBacklogRepository;
import edu.mum.mumscrum.repository.UserStoryRepository;
import edu.mum.mumscrum.response.ResponseStatusException;
import edu.mum.mumscrum.service.ReleaseBacklogService;

@Service
public class ReleaseBacklogServiceImpl implements ReleaseBacklogService {

	@Autowired
	ReleaseBacklogRepository releaseBacklogRepository ;
	@Autowired
	UserStoryRepository userStoryRepository;
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void add(ReleaseBacklog releaseBacklog) {
		releaseBacklogRepository.save(releaseBacklog);
	}

	@Override
	public ReleaseBacklog getDetail(long releaseBacklogId) {
		return releaseBacklogRepository.findOne(releaseBacklogId);
	}

	@Override
	public void edit(Long releaseBacklogId, ReleaseBacklog releaseBacklog) {
		ReleaseBacklog rb = releaseBacklogRepository.findOne(releaseBacklogId);
		releaseBacklogRepository.save(releaseBacklog);
	}

	@Override
	public List<ReleaseBacklog> getAllList() {
		// TODO Auto-generated method stub
		return (List<ReleaseBacklog>)releaseBacklogRepository.findAll();
	}
	

	@Override
	@Transactional
	public ReleaseBacklog getDetailWithUserStories(Long releaseBacklogId) {
		ReleaseBacklog rb = releaseBacklogRepository.findOne(releaseBacklogId);
		List<UserStory> userStories = new ArrayList<UserStory>();
		for(UserStory us: rb.getUserStories()){
			userStories.add(us);
		}
		rb.setUserStories(userStories);
		return rb;
	}

	@Override
	@Transactional
	public void addUserStory(Long releaseBacklogId, Long userStoryId) {
		ReleaseBacklog rb = releaseBacklogRepository.findOne(releaseBacklogId);
		UserStory us = userStoryRepository.findOne(userStoryId);
		if(rb == null){
			throw new ResponseStatusException("The requested release backlog doesn't exist");
		}
		if(us == null){
			throw new ResponseStatusException("The requested user story doesn't exist");
		}
		rb.getUserStories().add(us);
		us.setReleaseBacklog(rb);
		releaseBacklogRepository.save(rb);
	}

	@Override
	@Transactional
	public void removeUserStory(Long releaseBacklogId, Long userStoryId) {
		ReleaseBacklog rb = releaseBacklogRepository.findOne(releaseBacklogId);
		UserStory us = userStoryRepository.findOne(userStoryId);
		if(rb == null){
			throw new ResponseStatusException("The requested release backlog doesn't exist");
		}
		if(us == null){
			throw new ResponseStatusException("The requested user story doesn't exist");
		}
		rb.getUserStories().remove(us);
		us.setReleaseBacklog(null);
		releaseBacklogRepository.save(rb);
	}

	@Override
	public List<ReleaseBacklog> getReleaseBacklogForUser(String loggedinUsername) {
		Employee user = employeeRepository.getEmployeeByUsername(loggedinUsername);
		List<ReleaseBacklog> list = releaseBacklogRepository.getReleaseBacklogForUser(user);
		return list;
	}
	
	@Override
	@Transactional
	public Object getUserStoriesNotAddedToSprint(ReleaseBacklog releaseBacklog) {
		ReleaseBacklog rb = releaseBacklogRepository.findOne(releaseBacklog.getId());
		List<UserStory> result = new ArrayList<UserStory>();
		for(UserStory us : rb.getUserStories()){
			if(us.getSprint() == null){
				result.add(us);
			}
		}
		return result;
	}

	@Override
	@Transactional
	@PreAuthorize(value="hasRole('SCRUM_MASTER')")
	public void startRelease(ReleaseBacklog rb) {
		ReleaseBacklog releaseBacklog = releaseBacklogRepository.findOne(rb.getId());
		if (releaseBacklog == null)
			throw new ResponseStatusException("Requested release backlog doesn't exist");
		releaseBacklog.setStatus(Status.IN_PROGRESS);
		releaseBacklogRepository.save(releaseBacklog);
	}
	
	@Override
	@PreAuthorize(value = "hasRole('SCRUM_MASTER')")
	public void delete(Long id) {
		// TODO Auto-generated method stub
		releaseBacklogRepository.delete(id);
	}
}