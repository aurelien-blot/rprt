package com.cgi.java.FilRouge.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class PhaseRtu extends Phase {

	@OneToMany(mappedBy="phaseRtu")
	private List<Module> modules;
	
	@JsonCreator
	public PhaseRtu() {
		super();
		this.modules = new ArrayList<Module>();
		this.setPhaseType(EnumPhaseType.RTU);
	}
	
	public PhaseRtu(@NotBlank String name, Quote quote, List<Module> modules, EnumPhaseType phaseType) {
		super(name, quote, phaseType);
		this.modules = modules;
		this.setPhaseType(phaseType);
	}
	
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public void serialize(JsonGenerator jgen) throws IOException {
		super.serialize(jgen);
		jgen.writeNumberField("id", this.getId());
		jgen.writeFieldName("modules");
		jgen.writeStartArray();
		for(Module mod : this.getModules()) {
			mod.serialize(jgen);
		}
		jgen.writeEndArray();
		super.closeSerialize(jgen);
	}
	
	
}
