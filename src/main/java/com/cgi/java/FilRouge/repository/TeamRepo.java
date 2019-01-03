package com.cgi.java.FilRouge.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Team;


@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {
	

	public List<Team> findByContract(Contract contract);
	//public List<Team> findByProject(Project project);
	@Query(nativeQuery = true, value="SELECT * FROM team_project tp JOIN team t ON t.id = tp.team_id WHERE tp.project_id = ?1")
	public List<Team> findByProject(int projectId);
	
	@Modifying
	@Query(nativeQuery = true, value="DELETE FROM team_project WHERE project_id = ?1")
	public void cleanTeamFromOneProject(int projectId);
	
}
