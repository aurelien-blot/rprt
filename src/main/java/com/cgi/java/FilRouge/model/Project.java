package com.cgi.java.FilRouge.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "project")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String shortName;
	
	@NotBlank
	private String name;
	
	private String description;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date creationDate;
	
	private boolean isArchived;
	
	@ManyToMany(mappedBy = "projects")
	private List<Team> teams;
	/*
	@OneToMany(mappedBy="project")
	private List<Team> teams;
	*/
	@OneToMany(mappedBy="project")
	private List<Quote> quotes;
	
	@OneToOne(mappedBy="project")
	private ProjectSequence projectSequence;
	
	public Project() {
		this.teams = new ArrayList<Team>();
		this.quotes = new ArrayList<Quote>();
	}

	public Project(String shortName, @NotBlank String name) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.teams = new ArrayList<Team>();
		this.quotes = new ArrayList<Quote>();
		this.isArchived = false;
	}

	

	public ProjectSequence getProjectSequence() {
		return projectSequence;
	}

	public void setProjectSequence(ProjectSequence projectSequence) {
		this.projectSequence = projectSequence;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
