package com.cgi.java.FilRouge.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "agency")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Agency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private String name;
	

	@OneToMany(mappedBy="agency")
	private List<Territory> territories;
	
	
	public Agency() {
		this.territories = new ArrayList<Territory>();
	}
	public Agency(@NotBlank String name) {

		this.name = name;
		this.territories = new ArrayList<Territory>();
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

	public List<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(List<Territory> territories) {
		this.territories = territories;
	}
	

}
