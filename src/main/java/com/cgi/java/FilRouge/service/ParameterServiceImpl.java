package com.cgi.java.FilRouge.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.dto.ModuleDto;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Metric;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.repository.AbacusRepo;
import com.cgi.java.FilRouge.repository.MetricRepo;
import com.cgi.java.FilRouge.repository.ParameterRepo;
import com.cgi.java.FilRouge.service.base.BaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ParameterServiceImpl extends BaseService<ParameterRepo, Parameter>{

	@Autowired
	AbacusServiceImpl abacusServiceImpl;
	
	@Autowired
	MetricServiceImpl metricServiceImpl;
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<Parameter> findByAbacus(Abacus abacus) throws EntityNotFoundException{
		
		final List<Parameter> parameters = this.repository.findByAbacus(abacus);
		
		if( parameters == null ) {
			throw new EntityNotFoundException();
		}
	
		return parameters;
		
	}
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public Parameter findTheParameter(int abacusId, int metricId) throws EntityNotFoundException{
		
		Abacus abacus = abacusServiceImpl.findById(abacusId);
		Metric metric = metricServiceImpl.findById(metricId);
		
		final List<Parameter> parameters = repository.findByAbacusAndMetric(abacus, metric);
		
		if(parameters.size()!=1) {
			 return null;
		 }
		 return parameters.get(0);
	}
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public void deleteParametersFromAbacus(Abacus abacus) throws EntityNotFoundException{
		
		int abacusId = abacus.getId();
			
		this.repository.deleteParametersFromAbacus(abacusId);
	
	}
		
}
