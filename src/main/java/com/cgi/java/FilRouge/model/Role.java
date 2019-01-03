package com.cgi.java.FilRouge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * 	Les r�les sont des "groupes" dans lesquels les users peuvent appartenir
 * 	Les users ne peuvent poss�der qu'un seul role
 * 	Chaque r�le contient une liste de privileges accordant des droits
 * 
 * */
@Entity
@Table(name = "role")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private String label;
	
	@OneToMany(mappedBy="role")
	private List<User> users;
	

	@ManyToMany
	@JoinTable(name="role_privilege", joinColumns = { @JoinColumn(name = "role") }, inverseJoinColumns = { @JoinColumn(name = "privilege") })
	private List<Privilege> privileges;
	
	public Role() {
		this.users = new ArrayList<User>();
		this.privileges = new ArrayList<Privilege>();
	}

	public Role(@NotBlank String label) {
		this.label = label;
		this.users = new ArrayList<User>();
		this.privileges = new ArrayList<Privilege>();
	}
	
	public Role(@NotBlank String label, List<Privilege> privileges) {
		this(label);
		this.privileges = privileges;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	
	
	
}
