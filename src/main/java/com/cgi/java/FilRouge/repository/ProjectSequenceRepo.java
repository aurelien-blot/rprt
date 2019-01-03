package com.cgi.java.FilRouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.ProjectSequence;

public interface ProjectSequenceRepo extends JpaRepository<ProjectSequence, Integer>{
	
	public ProjectSequence findByProject(Project project);

}
