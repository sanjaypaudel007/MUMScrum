package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.entity.Pair;
import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.entity.UserStory;

public interface SprintService {
	
	public List<Sprint> getSprintFor(Long releaseBacklogId);

	public void add(Sprint sprint, Long releaseBacklogId);

	public Sprint getDetail(long sprintId);

	public void edit(Long sprintId, Sprint sprint);
	
	public List<Sprint> getAllList();
	
	public List< Pair<Integer, String> > getOptionList(Long releaseBacklogId);
	
	public Sprint getDetailWithUserStories(Long sprintId);

	public void addUserStory(Long sprintId, Long userStoryId);

	public void removeUserStory(Long sprintId, Long userStoryId);

	public void startSprint(Sprint sprint);

	public List<UserStory> getSprintUserStoriesForUser(Long sprintId, String username);

	public void updateEstimation(Sprint sprint, List<UserStory> userStories, String username);
	
	public void delete(Long id);
}
