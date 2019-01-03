package com.cgi.java.FilRouge.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Team;


@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
	
	public List<Project> findByTeamsIn(List<Team> teams);
	
	@Query(nativeQuery = true, value="SELECT * FROM project p JOIN `team_project`tp ON tp.project_id = p.id JOIN team t ON tp.team_id= t.id where p.is_archived=false and t.contract=?1 group by project_id")
	public List<Project> findByTeamsContractAndArchivedFalse(int contractId);
	
	@Query(nativeQuery = true, value="SELECT * FROM `team_project`tp JOIN project p ON tp.project_id = p.id where p.is_archived=false group by project_id")
	public List<Project> findDistinctByTeamsInAndArchivedFalse(List<Team> teams);
	
	public List<Project> findByNameLike(String like);

}