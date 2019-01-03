package com.cgi.java.FilRouge.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Amendment extends Quote{

	
	@ManyToOne
	@JoinColumn(name = "parent_quote"/*, nullable = false*/)
	private Quote parentQuote;
	
	public Amendment() {
		
	}

	public Amendment(Quote parentQuote) {
		super();
		this.parentQuote = parentQuote;
	}

	public Quote getParentQuote() {
		return parentQuote;
	}

	public void setParentQuote(Quote parentQuote) {
		this.parentQuote = parentQuote;
	}
	
	
}
