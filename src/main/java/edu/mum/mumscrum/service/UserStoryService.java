package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.entity.UserStory;

public interface UserStoryService {
//	public List<UserStory> getUserStoryFor(Long productBacklogId);

//	public void add(UserStory userStory, Long productBacklogId);

	public UserStory getDetail(long userStoryId);

	public void edit(Long userStoryId, UserStory userStory );
	
	public void edit(Long userStoryId, String name, String description, Double developmentEstimate);

	public List<UserStory> getAllListFor(String loggedInUsername);

}
