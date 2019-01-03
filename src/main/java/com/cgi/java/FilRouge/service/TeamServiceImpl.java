package com.cgi.java.FilRouge.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.repository.TeamRepo;
import com.cgi.java.FilRouge.service.base.BaseService;

@Service
public class TeamServiceImpl extends BaseService<TeamRepo, Team>{

	@Autowired
	private TeamRepo teamRepo;
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<Team> findByContract(Contract contract) throws EntityNotFoundException{
		
		final List<Team> teamsByContract = teamRepo.findByContract(contract);
		
		if( teamsByContract == null ) {
			throw new EntityNotFoundException();
		}
	
		return teamsByContract;
	}

	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<Team> findByProject(Project project) throws EntityNotFoundException{
		
		final List<Team> teams = teamRepo.findByProject(project.getId());
		
		if( teams == null ) {
			throw new EntityNotFoundException();
		}
	
		return teams;
	}
	
	public void addProject(Team team, Project project) {
		team.getProjects().add(project);
		this.save(team);
	}
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public void cleanTeamFromOneProject(Project project) throws EntityNotFoundException{
		this.repository.cleanTeamFromOneProject(project.getId());
	}

	
}
