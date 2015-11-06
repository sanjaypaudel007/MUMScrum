package edu.mum.mumscrum.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.mumscrum.entity.Role;



@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>
{
	@Query ("select r from Role r where name=:name")
	public Role getRoleByName( @Param("name") String name);

}
