package edu.mum.mumscrum.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.UserStory;
import edu.mum.mumscrum.entity.WorkLog;

@Repository
public interface WorkLogRepository extends CrudRepository<WorkLog, Long>{

	@Query("SELECT w FROM WorkLog w WHERE w.userStory = :us AND w.estimatingPerson = :employee")
	List<WorkLog> getWorkLogForUserStoryAndEmployee(@Param("us") UserStory userStory, @Param("employee") Employee emp);

	@Query("SELECT w FROM WorkLog w WHERE userStory = :us AND estimatingPerson = :emp AND date = :logdate")
	List<WorkLog> getExistingWorklog(@Param("us") UserStory userStory,@Param("emp") Employee employee, @Param("logdate") Date logDate);

	@Modifying
	@Transactional
	@Query("DELETE FROM WorkLog WHERE userStory = :us AND estimatingPerson = :emp AND date = :logdate")
	void deleteExistingWorklog(@Param("us") UserStory userStory,@Param("emp") Employee employee, @Param("logdate") Date logDate);

	@Query("SELECT w FROM WorkLog w WHERE estimatingPerson = :emp")
	List<WorkLog> getAllFor(@Param("emp") Employee employee);
	
	@Query("SELECT w FROM WorkLog w WHERE userStory = :us")
	List<WorkLog> findAll(@Param("us") UserStory userStory);
}
