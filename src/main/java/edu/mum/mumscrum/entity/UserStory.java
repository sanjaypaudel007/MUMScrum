package edu.mum.mumscrum.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import edu.mum.mumscrum.enums.Priority;
import edu.mum.mumscrum.enums.Status;

@Entity
public class UserStory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Size(min=4, max=200, message="Name should have min {2} and max {1} characters.")
	private String name;
	@NotEmpty(message="Description cannot be empty.")
	private String description;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@OneToOne
	@JoinColumn(name="Uploaded_File_Id")
	private UploadedFile attachment;
	@NotNull(message="Development estimate should be assigned.")
	private Double developmentEstimate;
	@NotNull(message="Testing estimate should be assigned.")
	private Double testingEstimate;

	@OneToOne
	@JoinColumn(name="releasebacklog_id")
	private ReleaseBacklog releaseBacklog;
	@OneToOne
	@JoinColumn(name="sprint_id")
	private Sprint sprint;
	@OneToOne
	@JoinColumn(name="developer_id")
	private Employee developer;
	@OneToOne
	@JoinColumn(name="tester_id")
	private Employee tester;
	@OneToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL, mappedBy="userStory")
	private List<WorkLog> workLogs;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public UploadedFile getAttachment() {
		return attachment;
	}
	public void setAttachment(UploadedFile attachment) {
		this.attachment = attachment;
	}
	public Double getDevelopmentEstimate() {
		return developmentEstimate;
	}
	public void setDevelopmentEstimate(Double developmentEstimate) {
		this.developmentEstimate = developmentEstimate;
	}
	public Double getTestingEstimate() {
		return testingEstimate;
	}
	public void setTestingEstimate(Double testingEstimate) {
		this.testingEstimate = testingEstimate;
	}
	public Sprint getSprint() {
		return sprint;
	}
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	public Employee getDeveloper() {
		return developer;
	}
	public void setDeveloper(Employee developer) {
		this.developer = developer;
	}
	public Employee getTester() {
		return tester;
	}
	public void setTester(Employee tester) {
		this.tester = tester;
	}
	public ReleaseBacklog getReleaseBacklog() {
		return releaseBacklog;
	}
	public void setReleaseBacklog(ReleaseBacklog releaseBacklog) {
		this.releaseBacklog = releaseBacklog;
	}
	public List<WorkLog> getWorkLogs() {
		return workLogs;
	}
	public void setWorkLogs(List<WorkLog> workLogs) {
		this.workLogs = workLogs;
	}
	public Map<Date, Double> getBurndownData(Date today) {
		List<WorkLog> generalizedWorklogs = generalize(today);
		Map<Date, Double> result = new HashMap<Date, Double>();
		for (WorkLog wl : generalizedWorklogs) {
			if (result.containsKey(wl.getDate())) {
				result.put(wl.getDate(), result.get(wl.getDate())+  wl.getRemainingWorkEstimation());
			} else {
				result.put(wl.getDate(), wl.getRemainingWorkEstimation());
			}
		}
		return result;
	}

	private List<WorkLog> generalize(Date today) {
		List<WorkLog> generalized = new ArrayList<WorkLog>();
		Date startDate = getEarliestDate(workLogs);
		if(!workLogs.isEmpty()){
			Map<Integer, WorkLog> previousWorklogForDeveloper = new HashMap<Integer, WorkLog>();
			Calendar c= Calendar.getInstance();
			c.setTime(startDate);
			while(c.getTime().compareTo(today) <= 0){
				List<WorkLog> worklogsForDate = getForDate(workLogs, c.getTime());
				for(WorkLog wl : worklogsForDate){
					previousWorklogForDeveloper.put(wl.getEstimatingPerson().getId(), wl);
				}
				for(WorkLog wl : previousWorklogForDeveloper.values()){
					WorkLog wl1 = new WorkLog();
					wl1.setDate(c.getTime());
					wl1.setRemainingWorkEstimation(wl.getRemainingWorkEstimation());
					wl1.setEstimatingPerson(wl.getEstimatingPerson());
					wl1.setUserStory(wl.getUserStory());
					generalized.add(wl1);
				}
				c.add(Calendar.DATE, 1);
			}
		}
		return generalized;
	}

	private List<WorkLog> getForDate(List<WorkLog> workLogs2, Date date) {
		List<WorkLog> result = new ArrayList<WorkLog>();
		for(WorkLog wl :workLogs2){
			if(wl.getDate().compareTo(date) == 0){
				result.add(wl);
			}
		}
		return result;
	}

	private Date getEarliestDate(List<WorkLog> workLogs2) {
		Date earliest = null;
		for (WorkLog wl : workLogs) {
			if(earliest == null){
				earliest = wl.getDate();
			}else{
				if(wl.getDate().compareTo(earliest) < 0){
					earliest = wl.getDate();
				}
			}
		}
		return earliest;
	}

	private List<WorkLog> getSortedWorklogs() {
		List<WorkLog> temp = new ArrayList<WorkLog>();
		for (WorkLog wl : workLogs) {
			boolean inserted = false;
			for (int i = 0; i < temp.size(); i++) {
				if (wl.getDate().compareTo(temp.get(i).getDate()) <= 0) {
					temp.add(i, wl);
					inserted = true;
					break;
				}
			}
			if (!inserted) {
				temp.add(wl);
			}
		}
		return temp;
	}
}