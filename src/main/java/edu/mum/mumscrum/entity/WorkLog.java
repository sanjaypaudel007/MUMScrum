package edu.mum.mumscrum.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class WorkLog {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Date date;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name="userstory_id")
	private UserStory userStory;
	@OneToOne
	@JoinColumn(name="employee_id")
	private Employee estimatingPerson;
	private Double remainingWorkEstimation;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public UserStory getUserStory() {
		return userStory;
	}
	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}
	public Employee getEstimatingPerson() {
		return estimatingPerson;
	}
	public void setEstimatingPerson(Employee estimatingPerson) {
		this.estimatingPerson = estimatingPerson;
	}
	public Double getRemainingWorkEstimation() {
		return remainingWorkEstimation;
	}
	public void setRemainingWorkEstimation(Double remainingWorkEstimation) {
		this.remainingWorkEstimation = remainingWorkEstimation;
	}
}
