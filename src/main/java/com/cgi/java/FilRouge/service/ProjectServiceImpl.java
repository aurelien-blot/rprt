package com.cgi.java.FilRouge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.repository.ProjectRepo;
import com.cgi.java.FilRouge.repository.TeamRepo;
import com.cgi.java.FilRouge.service.base.BaseService;

@Service
public class ProjectServiceImpl extends BaseService<ProjectRepo, Project>{

	@Autowired
	private TeamRepo teamRepo;
	
	@Autowired
	private ProjectRepo projectRepo;

	// Recherche des équipes en fonction du projet
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<Project> findDistinctByContract(Contract contract) throws EntityNotFoundException{
		
		final List<Project> projects = this.repository.findByTeamsContractAndArchivedFalse(contract.getId());
		
		if( projects == null ) {
			throw new EntityNotFoundException();
		}
	
		return projects;
	}
	
	
	// Recherche des projets en fonction d'une ou plusieurs équipes
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<Project> findDistinctByTeams(ArrayList<Team> teamlist) throws EntityNotFoundException{
		// LA IL FAUT LE BON ARGUMENT
		final List<Project> projects = this.repository.findDistinctByTeamsInAndArchivedFalse(teamlist);
		
		if( projects == null ) {
			throw new EntityNotFoundException();
		}
		
		return projects;
	}

	
	// Recherche rapide
	public List<Project> findBySearchQuery(String query){
		String[] explode = query.split(" ");
		String buffer = "";
		for(String ex : explode) {
			buffer += "%" + ex + "%";
		}
				
		List<Project> projects = this.repository.findByNameLike(buffer);
		
		return projects;
	}

}
