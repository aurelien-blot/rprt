package com.cgi.java.FilRouge.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(scope= User.class , generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User extends People{
	
	@Transient
	public final static String genericPassword = "cgicgi";
	
	@Column(unique=true)
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	boolean isActive;

	@ManyToOne
    @JoinColumn(name = "role"/*, nullable = false*/)
    private Role role;
	
	
	@OneToMany(mappedBy="creator")
	private List<Quote> createdQuotes;
	
	@OneToMany(mappedBy="templateCreator")
	private List<QuoteTemplate> createdQuoteTemplates;
	
	@OneToMany(mappedBy="validator")
	private List<Quote> validatedQuotes;
	
	public User() {
		super();
		this.isActive=true;
		this.createdQuotes=new ArrayList<Quote>();
		this.validatedQuotes = new ArrayList<Quote>();
		this.createdQuoteTemplates = new ArrayList<QuoteTemplate>();
	}

	public User(@NotBlank String firstname, @NotBlank String lastname, @NotNull Date birthdate,
			@NotBlank String staff_number, @NotBlank String username, @NotBlank String password, Role role, List<Team> teams) {
		super(firstname, lastname, birthdate, staff_number, teams);
		this.isActive=true;
		this.username = username;
		this.password = password;
		this.role = role;
		this.createdQuotes = new ArrayList<Quote>();
		this.validatedQuotes = new ArrayList<Quote>();
		this.createdQuoteTemplates = new ArrayList<QuoteTemplate>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Quote> getCreatedQuotes() {
		return createdQuotes;
	}

	public void setCreatedQuotes(List<Quote> createdQuotes) {
		this.createdQuotes = createdQuotes;
	}

	public List<Quote> getValidatedQuotes() {
		return validatedQuotes;
	}

	public void setValidatedQuotes(List<Quote> validatedQuotes) {
		this.validatedQuotes = validatedQuotes;
	}

	public List<QuoteTemplate> getCreatedQuoteTemplates() {
		return createdQuoteTemplates;
	}

	public void setCreatedQuoteTemplates(List<QuoteTemplate> createdQuoteTemplates) {
		this.createdQuoteTemplates = createdQuoteTemplates;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
	
}
