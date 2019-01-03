package com.cgi.java.FilRouge.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "territory")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Territory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name = "agency", nullable = false)
	private Agency agency;
	
	
	@OneToMany(mappedBy="territory")
	private List<Contract> contracts;
	
	public Territory() {
		this.contracts = new ArrayList<Contract>();
	}

	
	public Territory(@NotBlank String name, Agency agency) {
		super();
		this.name = name;
		this.agency = agency;
		this.contracts = new ArrayList<Contract>();
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


	public Agency getAgency() {
		return agency;
	}


	public void setAgency(Agency agency) {
		this.agency = agency;
	}


	public List<Contract> getContracts() {
		return contracts;
	}


	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	
	


	
	
	
}
