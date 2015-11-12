package edu.mum.mumscrum.entity;

import java.util.Date;
import java.util.List;

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

import edu.mum.mumscrum.enums.Status;

@Entity(name="ReleaseBacklog")
public class ReleaseBacklog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Size(min = 4, max = 100, message = "Name should have min {2} and max {1} characters.")
	private String name;

	@Column
	@NotEmpty(message = "Description cannot be empty.")
	private String description;

	private Date startDate;

	private Date deadline;

	@OneToMany(mappedBy="releaseBacklog", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private List<Sprint> sprints;
	
	@OneToMany(mappedBy="releaseBacklog", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private List<UserStory> userStories;
	
	@OneToOne
	@JoinColumn(name="scrummaster_employeeid")
	private Employee scrumMaster;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}

	public Employee getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(Employee scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

	public List<UserStory> getUserStories() {
		return userStories;
	}

	public void setUserStories(List<UserStory> userStories) {
		this.userStories = userStories;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
