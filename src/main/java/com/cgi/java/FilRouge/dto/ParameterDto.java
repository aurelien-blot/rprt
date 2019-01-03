package com.cgi.java.FilRouge.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ParameterDto.class)
public class ParameterDto {
	
	private int id;
	private String type;
	private String typology;
	private String task;
	private String complexity;
	private String interventionLevel;
	private double unitCharge;
	
	public ParameterDto() {
		
	}
	
	

	public ParameterDto(String type, String typology, String task, String complexity, String interventionLevel,
			double unitCharge) {
		this();
		this.type = type;
		this.typology = typology;
		this.task = task;
		this.complexity = complexity;
		this.interventionLevel = interventionLevel;
		this.unitCharge = unitCharge;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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

	public double getUnitCharge() {
		return unitCharge;
	}

	public void setUnitCharge(double unitCharge) {
		this.unitCharge = unitCharge;
	}
	

	
}
