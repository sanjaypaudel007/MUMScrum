package edu.mum.mumscrum.response;

import java.util.List;

import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.entity.UserStory;

public class EstimationModel {
	private Sprint sprint;
	private List<UserStory> userStories;
	public Sprint getSprint() {
		return sprint;
	}
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	public List<UserStory> getUserStories() {
		return userStories;
	}
	public void setUserStories(List<UserStory> userStories) {
		this.userStories = userStories;
	}
}
