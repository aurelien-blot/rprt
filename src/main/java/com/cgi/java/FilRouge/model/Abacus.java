package com.cgi.java.FilRouge.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "abacus")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Abacus.class)
public class Abacus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private String name;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
    private Date createdOn;
	
	private boolean isArchived;
	
	private boolean isFinished;
	
	@OneToMany(mappedBy="abacus")
	private List<Parameter> parameters;
	
	@OneToMany(mappedBy="abacus")
	private List<Quote> quotes;
	
	@ManyToOne
	@JoinColumn(name="contract", nullable=false)
	private Contract contract;
	
	public Abacus() {
		this.parameters = new ArrayList<Parameter>();
		this.quotes = new ArrayList<Quote>();
	}

	
	public Abacus(@NotBlank String name, Date createdOn, boolean isArchived, Contract contract) {
		this();
		this.name = name;
		this.createdOn = createdOn;
		this.isArchived = isArchived;
		this.contract = contract;
		this.isFinished= false;
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	
	public boolean isFinished() {
		return isFinished;
	}


	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}


	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	
}
