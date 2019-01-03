package com.cgi.java.FilRouge.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;

@Entity
@Table(name = "parameter")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Parameter.class)
public class Parameter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name ="metric", nullable = false)
	private Metric metric;

	@ManyToOne
	@JoinColumn(name ="abacus", nullable = false)
	private Abacus abacus;
	
	@OneToMany(mappedBy="parameter")
	private List<Module> modules;
	
	
	private double unitCharge;
	
	public Parameter() {
		this.modules = new ArrayList<Module>();
		this.unitCharge = 0d;
	}
	
	public Parameter(Abacus abacus, Metric metric, double unitCharge) {
		this();
		this.abacus = abacus;
		this.metric = metric;
		this.unitCharge = unitCharge;
		
	}

	
	
	public Metric getMetric() {
		return metric;
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Abacus getAbacus() {
		return abacus;
	}

	public void setAbacus(Abacus abacus) {
		this.abacus = abacus;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}


	public double getUnitCharge() {
		return unitCharge;
	}



	public void setUnitCharge(double unitCharge) {
		this.unitCharge = unitCharge;
	}
	
	public void serialize(JsonGenerator jgen, boolean isField) throws IOException {
		if(isField == true)
			jgen.writeFieldName("metric");
		
		jgen.writeStartObject();
			jgen.writeNumberField("id", this.getId());
/*			jgen.writeStringField("type", this.getType());
			jgen.writeStringField("typology", this.getTypology());
			jgen.writeStringField("task", this.getTask());
			jgen.writeStringField("complexity", this.getComplexity());
			jgen.writeStringField("interventionLevel", this.getInterventionLevel());*/
			jgen.writeNumberField("unitCharge", this.getUnitCharge());
		
		jgen.writeEndObject();
	}
	
	public void serialize(JsonGenerator jgen) throws IOException {
		this.serialize(jgen, false);
	}
	
}
