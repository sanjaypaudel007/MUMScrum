package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.entity.ReleaseBacklog;

public interface ReleaseBacklogService {

	public void add(ReleaseBacklog releaseBacklog);

	public ReleaseBacklog getDetail(long releaseBacklogId);

	public void edit(Long releaseBacklogId, ReleaseBacklog releaseBacklog);
	
	public List<ReleaseBacklog> getAllList();
	
	public ReleaseBacklog getDetailWithUserStories(Long releaseBacklogId);

	public void addUserStory(Long releaseBacklogId, Long userStoryId);

	public void removeUserStory(Long releaseBacklogId, Long userStoryId);

	public List<ReleaseBacklog> getReleaseBacklogForUser(String loggedinUsername);
	
	public Object getUserStoriesNotAddedToSprint(ReleaseBacklog releaseBacklog);

	public void startRelease(ReleaseBacklog rb);
	
	public void delete(Long id);
}