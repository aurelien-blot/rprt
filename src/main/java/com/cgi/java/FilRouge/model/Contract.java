package com.cgi.java.FilRouge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "contract")
//@JsonSerialize(using = ContractSerializer.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String client;
	

	@ManyToOne
	@JoinColumn(name = "territory", nullable = false)
	private Territory territory;
	

	@OneToMany(mappedBy="contract")
	private List<Team> teams;
	

	@OneToMany(mappedBy="contract")
	private List<Abacus> abaci;
	
	public Contract() {
		this.teams = new ArrayList<Team>();
		this.abaci = new ArrayList<Abacus>();
	}

	public Contract(@NotBlank String name, @NotBlank String client, Territory territory) {
		this.name = name;
		this.client = client;
		this.territory = territory;
		this.teams = new ArrayList<Team>();
		this.abaci = new ArrayList<Abacus>();
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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Abacus> getAbaci() {
		return abaci;
	}

	public void setAbaci(List<Abacus> abacus) {
		this.abaci = abacus;
	}
	
	
}
