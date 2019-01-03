package com.cgi.java.FilRouge.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Table(name = "phase")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
//	JsonTypeInfo permet de signifier au deserializer que le nom de la classe dérivée de Phase est contenu dans la propriété IClass de l'objet
@JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS,
	include = JsonTypeInfo.As.PROPERTY,
	property = "IClass"
)
@JsonSubTypes({
	@Type(value = PhaseRtu.class),
})

//@Indexed
public class Phase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	//@Field
	protected String name;
	
	@ManyToOne
	@JoinColumn(name="quote", nullable = true)
	protected Quote quote;
	
	@ManyToOne
	@JoinColumn(name="quotetemplate", nullable = true)
	protected QuoteTemplate quoteTemplate;
	
	@OneToMany(mappedBy="phase")
	private List<Hypothesis> hypothesis;
	
	private double value;
	
	private String justification;
	

	private EnumPhaseType phaseType;
	
	public Phase() {
		this.hypothesis = new ArrayList<Hypothesis>();
	}

	
	public Phase(@NotBlank String name, Quote quote) {
		this();
		this.name = name;
		this.quote = quote;
	}
	
	public Phase(@NotBlank String name, Quote quote, EnumPhaseType phaseType) {
		this(name, quote);
		this.phaseType = phaseType;
	}
	
	public Phase(@NotBlank String name, Quote quote, double value, List<Hypothesis> hypothesis, EnumPhaseType phaseType ) {
		this(name, quote, phaseType);
		this.value = value;
		this.hypothesis = hypothesis;
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



	public Quote getQuote() {
		return quote;
	}


	public void setQuote(Quote quote) {
		this.quote = quote;
	}


	public QuoteTemplate getQuoteTemplate() {
		return quoteTemplate;
	}


	public void setQuoteTemplate(QuoteTemplate quoteTemplate) {
		this.quoteTemplate = quoteTemplate;
	}


	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}


	public List<Hypothesis> getHypothesis() {
		return hypothesis;
	}

	public void setHypothesis(List<Hypothesis> hypothesis) {
		this.hypothesis = hypothesis;
	}
	
	public EnumPhaseType getPhaseType() {
		return phaseType;
	}

	public void setPhaseType(EnumPhaseType phaseType) {
		this.phaseType = phaseType;
	}

	
	public String getJustification() {
		return justification;
	}


	public void setJustification(String justification) {
		this.justification = justification;
	}


	//	jgen.writeEndObject sera exécuté dans les implémentations de Phase
	public void serialize(JsonGenerator jgen) throws IOException {
		//jgen.writeFieldName("globalWorkload");
		jgen.writeStartObject();
				
			jgen.writeNumberField("id", this.getId());
			jgen.writeStringField("name", this.getName());
			// obj hypothese
			jgen.writeNumberField("value", this.getValue());
			jgen.writeStringField("IClass", this.getClass().getName());
						
			
		//jgen.writeEndObject();
	}
	protected void closeSerialize(JsonGenerator jgen) throws IOException {
		jgen.writeEndObject();
	}
	
}
