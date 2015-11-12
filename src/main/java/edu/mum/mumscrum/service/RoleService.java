package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.entity.Role;

public interface RoleService {
	
	public Role add(Role role);
	
	public void edit(Role role);
	public void delete(Integer id);
	
	public Role getDetail(Integer id);
	
	public List<Role> getAllList();
	
}
