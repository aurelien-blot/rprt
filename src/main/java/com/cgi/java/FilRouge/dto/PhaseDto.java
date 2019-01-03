package com.cgi.java.FilRouge.dto;

import org.springframework.stereotype.Component;

import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.PhaseRtu;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Component
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=PhaseDto.class)


public class PhaseDto {
	private int id;

	private String name;
		
	private EnumPhaseType phaseType;
	
	private double value;
	
	private String justification;
	

	
	public PhaseDto() {
		this.justification =null;
	}
	
	public PhaseDto(int id, String name, String type , EnumPhaseType phaseType) {
		this();
		this.id=id;
		this.name=name;
		this.phaseType = phaseType;
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

	public EnumPhaseType getPhaseType() {
		return phaseType;
	}

	public void setPhaseType(EnumPhaseType typeId) {
		this.phaseType = typeId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
	
	
}
