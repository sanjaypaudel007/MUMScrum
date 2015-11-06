package edu.mum.mumscrum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.mumscrum.entity.Role;
import edu.mum.mumscrum.repository.RoleRepository;
import edu.mum.mumscrum.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role add(Role role) {
		roleRepository.save(role);
		return role;
	}

	@Override
	public void edit(Role role) {
		// TODO Auto-generated method stub
		roleRepository.save(role);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		roleRepository.delete(id);
	}

	@Override
	public Role getDetail(Integer id) {
		// TODO Auto-generated method stub
		return roleRepository.findOne(id);
	}

	@Override
	public List<Role> getAllList() {
		// TODO Auto-generated method stub
		return (List<Role>) roleRepository.findAll();
	}

}
