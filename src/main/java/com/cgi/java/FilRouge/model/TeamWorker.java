package com.cgi.java.FilRouge.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class TeamWorker extends People{


	@NotBlank
	private String position;
	
	public TeamWorker() {
		super();
	}

	public TeamWorker(@NotBlank String position) {
		this();
		this.position = position;
	}
	
	public TeamWorker(@NotBlank String firstname, @NotBlank String lastname, @NotNull Date birthdate,
			@NotBlank String staff_number, List<Team> teams, @NotBlank String position) {
		super(firstname, lastname, birthdate, staff_number, teams);
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}



	
	

}
