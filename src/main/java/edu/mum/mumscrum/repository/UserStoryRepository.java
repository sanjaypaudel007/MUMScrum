package edu.mum.mumscrum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.entity.ReleaseBacklog;
import edu.mum.mumscrum.entity.Sprint;
import edu.mum.mumscrum.entity.UserStory;
import edu.mum.mumscrum.enums.Status;

@Repository
public interface UserStoryRepository extends CrudRepository<UserStory, Long>{
	@Query("SELECT u FROM UserStory u WHERE releaseBacklog = :releasebacklog")
	public List<UserStory> getForUserStory(@Param("releasebacklog") ReleaseBacklog rb);

	@Query("SELECT u FROM UserStory u WHERE u.developer =:employee OR u.tester = :employee")
	public List<UserStory> getUserStoriesForEmployee(@Param("employee") Employee emp);

	@Query("SELECT u FROM UserStory u WHERE u.status = :userStoryStatus AND (u.developer =:employee OR u.tester = :employee) AND u.sprint = :s")
	public List<UserStory> getSprintUserStoriesForEstimation(@Param("s") Sprint sprint,@Param("employee") Employee developerOrTester, @Param("userStoryStatus") Status status);
}
