package com.cgi.java.FilRouge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "hypothesis")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Hypothesis {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String details;
	

	@ManyToOne
	@JoinColumn(name="phase", nullable=false)
	private Phase phase;
	
	public Hypothesis() {
		
	}

	public Hypothesis(String details, Phase phase) {
		super();
		this.details = details;
		this.phase = phase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
	
}
