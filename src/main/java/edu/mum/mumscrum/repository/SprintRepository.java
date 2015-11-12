package edu.mum.mumscrum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import edu.mum.mumscrum.entity.ReleaseBacklog;
import edu.mum.mumscrum.entity.Sprint;

@Repository
public interface SprintRepository extends CrudRepository< Sprint, Long> {

	@Query("SELECT s FROM Sprint s WHERE releaseBacklog = :releasebacklog")
	public List<Sprint> getForReleaseBacklog(@Param("releasebacklog") ReleaseBacklog releaseBacklog);
	
}