package edu.mum.mumscrum.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.mum.mumscrum.entity.WorkLog;
import edu.mum.mumscrum.response.WorkLogFormModel;

public interface WorkLogService {

	WorkLogFormModel getWorkLogFormData(String username);

	void add(WorkLogFormModel workFormData, String username);

	List<WorkLog> getAll(String username);

	Map<Date, Double> getBurndownData(Long sprintId);

}
