package com.cgi.java.FilRouge.model;

import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.cgi.java.FilRouge.enums.EnumStatus;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.ProjectSequenceServiceImpl;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "quote")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Quote.class)
public class Quote  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	protected String name;
	
	private String description;
	
	private String code;
	private double rtuAndOnRtuSubTotal;
	private double onGlobalSubTotal;
	private double withoutRatioSubTotal;
	private double total;

	@OneToMany(mappedBy="quote")
	private List<Phase> realPhases;
	
	private EnumStatus status;
	

	@ManyToOne
	@JoinColumn(name = "project"/*, nullable = false*/)
	private Project project;

	
	@ManyToOne
	@JoinColumn(name = "abacus"/*, nullable = false*/)
	private Abacus abacus;
	
	@Column(/*nullable = false, updatable = false*/)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;
	
	@Column(/*nullable = false*/)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedOn;
	

	@OneToMany(mappedBy="parentQuote")
	private List<Amendment> amendments;
	
	@ManyToOne
	@JoinColumn(name = "quote_template", nullable = true)
	private QuoteTemplate quoteTemplate;
	
	@ManyToOne
	@JoinColumn(name = "creator", nullable = true)
	private User creator;
	
	private Date validationWaitedOn;
	private Date rejectedOn;
	private Date cancelledOn;
	private Date validatedOn;
	private Date archivedOn;
		

	@ManyToOne
	@JoinColumn(name = "validator")
	private User validator;
	
	@ManyToMany
	@JoinTable(name="quote_team", joinColumns = { @JoinColumn(name = "quote") }, inverseJoinColumns = { @JoinColumn(name = "team") })
	private List<Team> teams;
	
	@Transient
	private PhaseRtu phaseRtu;
	
	public Quote() {
		super();
		this.amendments= new ArrayList<Amendment>();
		this.validationWaitedOn = null;
		this.rejectedOn = null;
		this.cancelledOn = null;
		this.validatedOn = null;
		this.archivedOn = null;
		this.validator = null;
		this.teams = new ArrayList<Team>();
		//this.createdOn = new Date();
	}
	
	
	public Quote(@NotBlank String name, User creator, QuoteTemplate template) {
		this();
		this.name = name;
		this.creator = creator;
		this.code = null;
		this.quoteTemplate = template;
		this.teams = new ArrayList<Team>();
	}
	
	
	public Quote(@NotBlank String name, String description,QuoteTemplate template, EnumStatus status, Abacus abacus, List<Team> teams, User creator/*,
			User validator*/) {
		this(name, creator, template);
		this.status = status;
		this.description = description;
		this.abacus = abacus;
		this.creator = creator;
		this.teams = teams;
		
		//this.validator = validator;
	}
	
	public Quote(@NotBlank String name, String description,QuoteTemplate template,	EnumStatus status, Project project, Abacus abacus, User creator, List<Team> teams) {	
		this(name, description, template, status, abacus, teams, creator/*, validator*/);
		this.project = project;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getValidationWaitedOn() {
		return validationWaitedOn;
	}

	public void setValidationWaitedOn(Date validationWaitedOn) {
		this.validationWaitedOn = validationWaitedOn;
	}

	public Date getRejectedOn() {
		return rejectedOn;
	}

	public void setRejectedOn(Date rejectedOn) {
		this.rejectedOn = rejectedOn;
	}

	public Date getCancelledOn() {
		return cancelledOn;
	}

	public void setCancelledOn(Date cancelledOn) {
		this.cancelledOn = cancelledOn;
	}

	public Date getValidatedOn() {
		return validatedOn;
	}

	public void setValidatedOn(Date validatedOn) {
		this.validatedOn = validatedOn;
	}

	public Date getArchivedOn() {
		return archivedOn;
	}

	public void setArchivedOn(Date archivedOn) {
		this.archivedOn = archivedOn;
	}

	public User getValidator() {
		return validator;
	}

	public void setValidator(User validator) {
		this.validator = validator;
	}

	public String getCode() {
		//METHODE POUR RETOURNER LA CONCAT DE L'ID / REGION /CONTRAT /...
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Amendment> getAmendments() {
		return amendments;
	}

	public void setAmendments(List<Amendment> amendments) {
		this.amendments = amendments;
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


	public QuoteTemplate getQuoteTemplate() {
		return quoteTemplate;
	}


	public void setQuoteTemplate(QuoteTemplate quoteTemplate) {
		this.quoteTemplate = quoteTemplate;
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


	public List<Phase> getRealPhases() {
		return realPhases;
	}


	public void setRealPhases(List<Phase> realPhases) {
		this.realPhases = realPhases;
	}
	
	public PhaseRtu getPhaseRtu() {
		for(int i=0; i<=this.getRealPhases().size();i++) {
			if(this.getRealPhases().get(i) instanceof PhaseRtu) {
				return (PhaseRtu)this.getRealPhases().get(i);
			}
		}
		return null;
	}
	
	public void setPhaseRtu(PhaseRtu phaseRtu) {
		this.phaseRtu = phaseRtu;
	}


	public List<Team> getTeams() {
		return teams;
	}


	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	
}
