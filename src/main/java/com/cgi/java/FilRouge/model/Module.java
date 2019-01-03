package com.cgi.java.FilRouge.model;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;

@Entity
@Table(name = "module")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Module.class)
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String name;
	
	@ManyToOne
	@JoinColumn(name="phaseRtu",nullable = false)
	private PhaseRtu phaseRtu;
	
	@ManyToOne
	@JoinColumn(name="parameter",nullable = false)
	private Parameter parameter;

	private double revisedCharge;
	
	public double getRevisedCharge() {
		return revisedCharge;
	}

	public void setRevisedCharge(double revisedCharge) {
		this.revisedCharge = revisedCharge;
	}

	public Module() {
		this.revisedCharge = 0;
	}
	
	public Module(@NotNull String name, PhaseRtu phaseRtu, Parameter parameter, double revisedCharge) {
		super();
		this.name = name;
		this.phaseRtu = phaseRtu;
		this.parameter = parameter;
		this.revisedCharge= revisedCharge;
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

	public PhaseRtu getPhaseRtu() {
		return phaseRtu;
	}

	public void setPhaseRtu(PhaseRtu phaseRtu) {
		this.phaseRtu = phaseRtu;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	
	public void serialize(JsonGenerator jgen) throws IOException {
		jgen.writeStartObject();

			jgen.writeNumberField("id", this.getId());
			jgen.writeStringField("name", this.getName());
			jgen.writeNumberField("revisedCharge", this.getRevisedCharge());
			this.parameter.serialize(jgen, true);
		jgen.writeEndObject();
	}
	
	
}
