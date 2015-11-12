package edu.mum.mumscrum.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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


@Entity(name="Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty
	@Column(name="firstname")
	private String firstName;
	@NotEmpty
	@Column(name="lastname")
	private String lastName;
	
	@Column
	@NotEmpty(message = "Enter {0}")
	private String address;
	
	private String phone;

	@Email
	@NotEmpty
	@Column(name = "email")
	private String email;

	@NotNull(message = "Valid date dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dob;

	private Double salary;
	
	@Column
	private String imageUrl="default.png";
	@Transient
	private MultipartFile image;

	private Boolean isActivated;
	
	@NotEmpty(message = "Enter {0}")
	@Size(min=4, max=20, message="Username should have length between {2} and {1}")
	private String username;
	
//	@NotEmpty(message = "Enter {0}")
	@Column(name = "password")
	private String password;

	@Transient
	private String rePassword;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	@Valid
	private Role role;
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="productOwner",cascade=CascadeType.ALL)
//	private List<ProductBacklog> lstProductBacklog;

	public Integer getId() {
		if(id == null) return 0;
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean getEnablePasswordChange() {
		return enablePasswordChange;
	}

	public void setEnablePasswordChange(boolean enablePasswordChange) {
		this.enablePasswordChange = enablePasswordChange;
	}

	@Transient
	private boolean enablePasswordChange;
//
//	public List<ProductBacklog> getListProductBacklog() {
//		return listProductBacklog;
//	}
//
//	public void setListProductBacklog(List<ProductBacklog> listProductBacklog) {
//		this.listProductBacklog = listProductBacklog;
//	}
	
	public String getFullName(){
		return this.lastName + ", " + this.firstName;
	}
}
