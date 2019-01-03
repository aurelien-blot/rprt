package com.cgi.java.FilRouge.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;


@Entity
@Table(name = "people")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")

//JsonTypeInfo permet de signifier au deserializer que le nom de la classe dérivée de People est contenu dans la propriété IClass de l'objet
/*@JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS,
include = JsonTypeInfo.As.PROPERTY,
property = "IClass"
)
@JsonSubTypes({
	@Type(value = TeamWorker.class),
	@Type(value = User.class)
})*/
public abstract class People {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private String firstname;

	@NotBlank
	private String lastname;

	private Date birthdate;
	
	private String staffNumber;
	

	@ManyToMany
	@JoinTable(name="team_people", joinColumns = { @JoinColumn(name = "people_id") }, inverseJoinColumns = { @JoinColumn(name = "team_id") })
	private List<Team> teams;
	

	public People() {
		this.teams = new ArrayList<>();
		
	}

	public People(@NotBlank String firstname, @NotBlank String lastname, @NotNull Date birthdate,
			@NotBlank String staff_number, List<Team> teams) {
		this();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.staffNumber = staff_number;
		this.teams = teams;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}


	public String getStaffNumber() {
		return staffNumber;
	}


	public void setStaffNumber(String staff_number) {
		this.staffNumber = staff_number;
	}
	
	public List<Team> getTeams() {
		return teams;
	}


	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	

	

	
}
