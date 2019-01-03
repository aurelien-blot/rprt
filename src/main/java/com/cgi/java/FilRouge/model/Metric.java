package com.cgi.java.FilRouge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Metric {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToMany(mappedBy="metric")
	private List<Parameter> parameters;
	
	private String type;
	private String typology;
	private String task;
	private String complexity;
	private String interventionLevel;
	
	
	public Metric() {
		super();
		this.parameters = new ArrayList<Parameter>();
		this.type = "";
		this.typology = "";
		this.task = "";
		this.complexity = "";
		this.interventionLevel = "";
	}
		
	public Metric( String type, String typology,
			String task, String complexity, String interventionLevel) {
		this();
		this.type = type;
		this.typology = typology;
		this.task = task;
		this.complexity = complexity;
		this.interventionLevel = interventionLevel;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypology() {
		return typology;
	}
	public void setTypology(String typology) {
		this.typology = typology;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getComplexity() {
		return complexity;
	}
	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}
	public String getInterventionLevel() {
		return interventionLevel;
	}
	public void setInterventionLevel(String interventionLevel) {
		this.interventionLevel = interventionLevel;
	}
	
	
	
}
