package edu.mum.mumscrum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.mumscrum.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	
	@Query("select m from Employee m where m.username = :username")
	Employee getEmployeeByUsername(@Param("username") String username);

	@Query("select m from Employee m where id = :memberId")
	Employee getEmployeeById(@Param("memberId")int memberId);
	
	@Query("select m from Employee m where username = :name")
	List<Employee> getEmployeeByName(@Param("name") String name);

	@Query("SELECT m from Employee m INNER JOIN m.roles r where r.role = :role_name")
	List<Employee> getEmployeesByRole(@Param("role_name") String role);
	
	

}
