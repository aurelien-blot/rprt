package com.cgi.java.FilRouge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Les privileges correspondent � des droits que les utilisateurs peuvent porter (ex : droit de consulter les devis)
 * Ils seront sous la forme suivante "ROLE_QUOTE_READ", "ROLE_QUOTE_WRITE"
 * La granularit� READ et WRITE est diff�renci�e
 * 
 * */

@Entity
@Table(name = "privilege")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Privilege {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	/**
	 * 	Le label contient le nom du r�le ( ex : ROLE_QUOTE_READ )
	 */
	@NotBlank
	private String label;
	
	@NotBlank
	private String description;
	

	@ManyToMany(mappedBy = "privileges")
	private List<Role> roles;
	
	public Privilege() {
		this.roles= new ArrayList<Role>();
	
	}

	public Privilege(@NotBlank String label, @NotBlank String description) {
		super();
		this.label = label;
		this.description = description;
		this.roles = new ArrayList<Role>();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
