package edu.mum.mumscrum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.ReleaseBacklog;


@Repository
public interface ReleaseBacklogRepository extends CrudRepository<ReleaseBacklog, Long>{
	

	@Query("SELECT r FROM ReleaseBacklog r WHERE r.scrumMaster = :user")
	public List<ReleaseBacklog> getReleaseBacklogForUser(@Param("user") Employee loggedinUsername);
}
