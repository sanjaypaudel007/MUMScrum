package edu.mum.mumscrum.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mum.edu.mumscrum.entities.Employee;
import mum.edu.mumscrum.entities.Pair;
import mum.edu.mumscrum.entities.ProductBacklog;
import mum.edu.mumscrum.entities.ReleaseBacklog;
import mum.edu.mumscrum.entities.UserStory;
import mum.edu.mumscrum.enums.Status;
import mum.edu.mumscrum.repository.EmployeeRepository;
import mum.edu.mumscrum.repository.ProductBacklogRepository;
import mum.edu.mumscrum.repository.ReleaseBacklogRepository;
import mum.edu.mumscrum.repository.UserStoryRepository;
import mum.edu.mumscrum.response.ResponseStatusException;
import mum.edu.mumscrum.service.ReleaseBacklogService;

@Service
public class ReleaseBacklogServiceImpl implements ReleaseBacklogService {

	@Autowired
	ReleaseBacklogRepository releaseBacklogRepository ;
	@Autowired
	ProductBacklogRepository productBacklogRepository;
	@Autowired
	UserStoryRepository userStoryRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<ReleaseBacklog> getReleaseBacklogFor(Long productBacklogId) {
		ProductBacklog pb = productBacklogRepository.findOne(productBacklogId);
		List<ReleaseBacklog> list = releaseBacklogRepository.getForProductBacklog(pb);
		return list;
	}

	@Override
	public void add(ReleaseBacklog releaseBacklog , Long productBacklogId) {
		ProductBacklog pb = productBacklogRepository.findOne(productBacklogId);
		releaseBacklog.setProductBacklog(pb);
		releaseBacklog.setStatus(Status.NEW);
		releaseBacklogRepository.save(releaseBacklog);
	}

	@Override
	public ReleaseBacklog getDetail(long releaseBacklogId) {
		return releaseBacklogRepository.findOne(releaseBacklogId);
	}

	@Override
	public void edit(Long releaseBacklogId, ReleaseBacklog releaseBacklog) {
		ReleaseBacklog rb = releaseBacklogRepository.findOne(releaseBacklogId);
		releaseBacklog.setProductBacklog(rb.getProductBacklog());
		releaseBacklogRepository.save(releaseBacklog);
	}

	@Override
	public List<ReleaseBacklog> getAllList() {
		// TODO Auto-generated method stub
		return (List<ReleaseBacklog>)releaseBacklogRepository.findAll();
	}
	
	@Override
	public List< Pair<Integer, String> > getOptionList(Long productBacklogId) {
		List< Pair<Integer, String>> optionList = new ArrayList< Pair<Integer,String>>();
		ProductBacklog productBacklog = productBacklogRepository.findOne(productBacklogId);
		List<ReleaseBacklog> releaseBacklogList = releaseBacklogRepository.getForProductBacklog(productBacklog);
		
		if(releaseBacklogList != null){
			for(ReleaseBacklog releaseBacklog: releaseBacklogList){
				optionList.add(new Pair(releaseBacklog.getId(), releaseBacklog.getName()));
			}
		}
		return optionList;
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
	@PreAuthorize(value="hasRole('PRODUCT_OWNER')")
	public void setScrumMaster(ReleaseBacklog changedReleaseBacklog, Integer scrumMasterId) {
		ReleaseBacklog releaseBacklog = releaseBacklogRepository.findOne(changedReleaseBacklog.getId());
		if (releaseBacklog == null)
			throw new ResponseStatusException("Requested release backlog doesn't exist");
		Employee scrumMaster = employeeRepository.findOne(scrumMasterId);
		if (scrumMaster == null)
			throw new ResponseStatusException("Requested scrum master doesn't exist");
		releaseBacklog.setScrumMaster(scrumMaster);
		releaseBacklog.setStartDate(changedReleaseBacklog.getStartDate());
		releaseBacklog.setDeadline(changedReleaseBacklog.getDeadline());
		releaseBacklog.setStatus(Status.ASSIGNED);
		releaseBacklog.getProductBacklog().setStatus(Status.IN_PROGRESS);
		productBacklogRepository.save(releaseBacklog.getProductBacklog());
		releaseBacklogRepository.save(releaseBacklog);
	}
}
