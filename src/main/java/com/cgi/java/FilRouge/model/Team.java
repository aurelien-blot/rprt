package com.cgi.java.FilRouge.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "team")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	

	@NotBlank
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "contract", nullable = false)
	private Contract contract;
	
	@ManyToMany(mappedBy = "teams")
	private List<People> peoples;
	
	@ManyToMany(cascade = { 
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
	    @JoinTable(name = "team_project",
	        joinColumns = @JoinColumn(name = "team_id"),
	        inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projects;
	/*
	@ManyToOne
	@JoinColumn(name = "project", nullable = false)
	private Project project;
	*/
	@ManyToMany(mappedBy = "teams")
	private List<Quote> quotes;
	
	public Team() {
		this.peoples = new ArrayList<People>();
		this.quotes = new ArrayList<Quote>();
		this.projects = new ArrayList<Project>();
	}

	public Team(@NotBlank String name, Contract contract) {
		super();
		this.name = name;
		this.contract = contract;
		this.projects = new ArrayList<Project>();
		this.peoples = new ArrayList<People>();
		this.quotes = new ArrayList<Quote>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public List<People> getPeoples() {
		return peoples;
	}

	public void setPeoples(List<People> peoples) {
		this.peoples = peoples;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
}
