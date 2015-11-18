package edu.mum.mumscrum.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import edu.mum.mumscrum.enums.Status;

@Entity
public class Sprint {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String name;
	
	private String description;

	@NotNull(message = "Valid date MM/dd/yyyy")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@NotNull(message = "Valid date MM/dd/yyyy")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "releaseBacklog_id")
	private ReleaseBacklog releaseBacklog;
	
	@OneToMany(mappedBy = "sprint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserStory> userStories;
	
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

	public ReleaseBacklog getReleaseBacklog() {
		return releaseBacklog;
	}

	public void setReleaseBacklog(ReleaseBacklog releaseBacklog) {
		this.releaseBacklog = releaseBacklog;
	}

	public List<UserStory> getUserStories() {
		return userStories;
	}

	public void setUserStories(List<UserStory> userStories) {
		this.userStories = userStories;
	}

	public Map<Date, Double> generateBurndownChart(Date today) {
		Map<Date, Double> result = new TreeMap<Date, Double>(); // TreeMap
																// automatically
																// sorts
		for (UserStory us : userStories) {
			Map<Date, Double> burnDownForUserStory = us.getBurndownData(today);
			Iterator<Date> datesIterator = burnDownForUserStory.keySet().iterator();
			while (datesIterator.hasNext()) {
				Date date = datesIterator.next();
				if (result.containsKey(date)) {
					result.put(date, result.get(date) + burnDownForUserStory.get(date));
				} else {
					result.put(date, burnDownForUserStory.get(date));
				}
			}
		}
		return result;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}