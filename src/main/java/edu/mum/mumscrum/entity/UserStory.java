package edu.mum.mumscrum.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import edu.mum.mumscrum.enums.Priority;
import edu.mum.mumscrum.enums.Status;

@Entity(name="UserStory")
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
}
