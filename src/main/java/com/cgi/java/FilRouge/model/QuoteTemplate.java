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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "quote_template")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class QuoteTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	protected String name;
	
	@OneToMany(mappedBy="quoteTemplate")
	private List<Phase> templatedPhases;
	
	@ManyToOne
	@JoinColumn(name = "templateCreator", nullable = true)
	private User templateCreator;
	
	@OneToMany(mappedBy="quoteTemplate")
	private List<Quote> quotes;
	
	public QuoteTemplate() {
		this.templatedPhases = new ArrayList<Phase>();
		this.quotes = new ArrayList<Quote>();
	}
	
	public QuoteTemplate(@NotBlank String name) {
		this();
		this.name = name;
	}

	public QuoteTemplate(@NotBlank String name, List<Phase> templatedPhases, User templateCreator, List<Quote> quotes) {
		this(name);
		this.templateCreator= templateCreator;
		this.templatedPhases = templatedPhases;
		this.quotes = quotes;
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


	public List<Phase> getTemplatedPhases() {
		return templatedPhases;
	}

	public void setTemplatedPhases(List<Phase> templatedPhases) {
		this.templatedPhases = templatedPhases;
	}

	public User getTemplateCreator() {
		return templateCreator;
	}

	public void setTemplateCreator(User templateCreator) {
		this.templateCreator = templateCreator;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}
	
	
	
}
