package com.cgi.java.FilRouge.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cgi.java.FilRouge.enums.EnumStatus;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Module;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.PhaseRtu;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.QuoteTemplate;
import com.cgi.java.FilRouge.model.Team;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=QuoteFormDto.class)
public class QuoteFormDto {
	

	private int id;

	private String name;
	
	private String description;
	
	private String code;
	
	private Project project;
	
	private Abacus abacus;
	
	
	private List<Phase> phases;
	
	private Date createdOn;
	
	private List<Module> modules;
	
	private QuoteTemplate quoteTemplate;

	private EnumStatus status;
	
	private double rtuAndOnRtuSubTotal;
	
	private double onGlobalSubTotal;
	
	private double withoutRatioSubTotal;
	
	private double total;
	
	private PhaseRtu phaseRtu;
	
	private List<Team> teams;
	
	public QuoteFormDto() {
		this.phases = new ArrayList<Phase>();
		this.modules = new ArrayList<Module>();
		this.teams = new ArrayList<Team>();
		 
	}
	
	public QuoteFormDto(int id, String name, String description, String code,
			Project project, Abacus abacus) {
		super();
		this.name = name;
		this.description = description;
		this.code = code;
		this.project = project;
		this.abacus = abacus;

		this.id=id;
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Abacus getAbacus() {
		return abacus;
	}
	public void setAbacus(Abacus abacus) {
		this.abacus = abacus;
	}
	
	public List<Phase> getPhases() {
		return phases;
	}
	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}
	
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public QuoteTemplate getQuoteTemplate() {
		return quoteTemplate;
	}
	public void setQuoteTemplate(QuoteTemplate quoteTemplate) {
		this.quoteTemplate = quoteTemplate;
	}

	
	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
	public String getCreatedOnFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(this.createdOn);
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public double getRtuAndOnRtuSubTotal() {
		return rtuAndOnRtuSubTotal;
	}

	public void setRtuAndOnRtuSubTotal(double rtuAndOnRtuSubTotal) {
		this.rtuAndOnRtuSubTotal = rtuAndOnRtuSubTotal;
	}

	public double getOnGlobalSubTotal() {
		return onGlobalSubTotal;
	}

	public void setOnGlobalSubTotal(double onGlobalSubTotal) {
		this.onGlobalSubTotal = onGlobalSubTotal;
	}

	public double getWithoutRatioSubTotal() {
		return withoutRatioSubTotal;
	}

	public void setWithoutRatioSubTotal(double withoutRatioSubTotal) {
		this.withoutRatioSubTotal = withoutRatioSubTotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	public PhaseRtu getPhaseRtu() {
		for(int i=0; i<=this.getPhases().size();i++) {
			if(this.getPhases().get(i) instanceof PhaseRtu) {
				return (PhaseRtu)this.getPhases().get(i);
			}
		}
		return null;
	}
	
	public void setPhaseRtu(PhaseRtu phaseRtu) {
		this.phaseRtu = phaseRtu;
	}
	

}
