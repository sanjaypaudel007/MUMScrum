package edu.mum.mumscrum.response;

import java.util.Date;

import edu.mum.mumscrum.entity.UserStory;

public class WorkLogFormModelEntry {
	private UserStory userStory;
	private Date lastLogDate;
	private Double lastLogEstimation;
	private Double newEstimation;
	public UserStory getUserStory() {
		return userStory;
	}
	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}
	public Date getLastLogDate() {
		return lastLogDate;
	}
	public void setLastLogDate(Date lastLogDate) {
		this.lastLogDate = lastLogDate;
	}
	public Double getLastLogEstimation() {
		return lastLogEstimation;
	}
	public void setLastLogEstimation(Double lastLogEstimation) {
		this.lastLogEstimation = lastLogEstimation;
	}
	public Double getNewEstimation() {
		return newEstimation;
	}
	public void setNewEstimation(Double newEstimation) {
		this.newEstimation = newEstimation;
	}

}
