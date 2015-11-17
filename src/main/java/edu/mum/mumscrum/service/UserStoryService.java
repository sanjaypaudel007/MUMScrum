package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.entity.UserStory;




public interface UserStoryService {
	//public List<UserStory> getUserStoryFor(Long releaseBacklogId);

	public void add(UserStory userStory);

	public UserStory getDetail(long userStoryId);

	public void edit(Long userStoryId, UserStory userStory );
	
	public void edit(Long userStoryId, String name, String description, Double developmentEstimate);

	public List<UserStory> getAllListFor(String loggedInUsername);

	public void updateUserStory(UserStory userStory);

	public void updateEstimation(UserStory userStory, String username);
}
