package edu.mum.mumscrum.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name="Role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String name;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="role",cascade=CascadeType.ALL)
	private List<Employee> listEmployee;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}


}

