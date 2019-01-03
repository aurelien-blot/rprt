package com.cgi.java.FilRouge.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Metric;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.repository.MetricRepo;
import com.cgi.java.FilRouge.service.base.BaseService;
import net.minidev.json.JSONObject;

@Service
public class MetricServiceImpl extends BaseService<MetricRepo, Metric>{
	
	public JSONObject findDistinctValuesFromColumnAndAbacus(int abacusId){
		
		JSONObject distinctsList = new JSONObject();
		distinctsList.appendField("complexity", repository.findDistinctComplexityFromAbacus(abacusId));
		distinctsList.appendField("task", repository.findDistinctTaskFromAbacus(abacusId));
		distinctsList.appendField("type", repository.findDistinctTypeFromAbacus(abacusId));
		distinctsList.appendField("typology", repository.findDistinctTypologyFromAbacus(abacusId));
		distinctsList.appendField("interventionLvl", repository.findDistinctInterventionLvlFromAbacus(abacusId));
		
		return distinctsList;
	}
	
	public Metric findTheMetric(String complexity, String interventionLvl,String task,String type,String typology,int abacusId){
		 List<Metric> metrics = repository.findOneMetricInAbacus(complexity, interventionLvl, task, type, typology, abacusId);
		 if(metrics.size()!=1) {
			 return null;
		 }
		 return metrics.get(0);
	}
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<String> findDistinctTypology() throws EntityNotFoundException{
		
		final List<String> typologies = this.repository.findDistinctTypology();
		
		if( typologies == null ) {
			throw new EntityNotFoundException();
		}
	
		return typologies;
		
	}
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<String> findDistinctType() throws EntityNotFoundException{
		
		final List<String> types = this.repository.findDistinctType();
		
		if( types == null ) {
			throw new EntityNotFoundException();
		}
	
		return types;
		
	}
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<String> findDistinctRole() throws EntityNotFoundException{
		
		final List<String> roles = this.repository.findDistinctRole();
		
		if( roles == null ) {
			throw new EntityNotFoundException();
		}
	
		return roles;
		
	}
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<String> findDistinctInterventionLvl() throws EntityNotFoundException{
		
		final List<String> interventionLvls = this.repository.findDistinctInterventionLvl();
		
		if( interventionLvls == null ) {
			throw new EntityNotFoundException();
		}
	
		return interventionLvls;
		
	}
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<String> findDistinctComplexity() throws EntityNotFoundException{
		
		final List<String> complexities = this.repository.findDistinctComplexity();
		
		if( complexities == null ) {
			throw new EntityNotFoundException();
		}
	
		return complexities;
		
	}
	
	public Metric generateAndSaveMetricIfDontExist( String type,  String typology,  String task,  String interventionLevel,  String complexity) {
		
		Metric metric;
		List<Metric> metrics =this.repository.findAMetric(complexity, interventionLevel, task, type, typology);
		if(metrics.size()>0) {
			metric = metrics.get(0);
		}
		else {
			metric = new Metric( type, typology, task, interventionLevel, complexity);
			this.save(metric);
		}
		
		return metric;
	}
}
