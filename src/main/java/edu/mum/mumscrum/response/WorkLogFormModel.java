package edu.mum.mumscrum.response;

import java.util.Date;
import java.util.List;

public class WorkLogFormModel {
	
	private Date today;
	
	private List<WorkLogFormModelEntry> entries;

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public List<WorkLogFormModelEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<WorkLogFormModelEntry> entries) {
		this.entries = entries;
	}
}
