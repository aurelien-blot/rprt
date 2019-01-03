package com.cgi.java.FilRouge.dto;

import org.springframework.stereotype.Component;

import com.cgi.java.FilRouge.model.Parameter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ModuleDto.class)
public class ModuleDto {

	private int id;

	private String name;
	
	private Parameter parameter;
	
	private int parameterId;
	
	private double revisedCharge;
	
	public ModuleDto() {
		
	}

	public ModuleDto(int id, String name, Parameter parameter, double revisedCharge, int parameterId) {
		this();
		this.id = id;
		this.name = name;
		this.parameter = parameter;
		this.revisedCharge = revisedCharge;
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

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public double getRevisedCharge() {
		return revisedCharge;
	}

	public void setRevisedCharge(double revisedCharge) {
		this.revisedCharge = revisedCharge;
	}

	public int getParameterId() {
		return parameterId;
	}

	public void setParameterId(int parameterId) {
		this.parameterId = parameterId;
	}
	
	
}
