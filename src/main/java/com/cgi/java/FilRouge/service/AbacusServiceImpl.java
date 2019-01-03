package com.cgi.java.FilRouge.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.model.serializer.AdminParameterSerializer;
import com.cgi.java.FilRouge.repository.AbacusRepo;
import com.cgi.java.FilRouge.service.base.BaseService;
import com.cgi.java.FilRouge.service.dto.ParameterDtoServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Service
public class AbacusServiceImpl extends BaseService<AbacusRepo, Abacus>{

	@Autowired
	ParameterServiceImpl parameterServiceImpl;

	@Autowired
	AbacusServiceImpl abacusServiceImpl;
	
	@Autowired
	ContractServiceImpl contractServiceImpl;
	
	@Autowired
	ParameterDtoServiceImpl parameterDtoServiceImpl;
	

	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<Abacus> findByContract(Contract contract) throws EntityNotFoundException{
		
		final List<Abacus> abacus = this.repository.findByContract(contract);
		
		if( abacus == null ) {
			throw new EntityNotFoundException();
		}
	
		return abacus;
		
	}
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<Abacus> findUsablesAbaciiByContract(Contract contract) throws EntityNotFoundException{
		
		final List<Abacus> abacii = this.repository.findUsablesAbaciiByContract(contract);
		
		if( abacii == null ) {
			throw new EntityNotFoundException();
		}
	
		return abacii;
		
	}
	
	
	public void archiveAbacus(Abacus abacus) {
		if(abacus.isFinished()) {
			abacus.setArchived(true);
		}
		
		this.save(abacus);
	}

	public String serializeToCreateAbacus(Abacus abacus) {
		
		List<Parameter> parameters = abacus.getParameters();		
		SimpleModule module = new SimpleModule();
		module.addSerializer(Parameter.class, new AdminParameterSerializer());
		ObjectMapper map = new ObjectMapper();
		map.registerModule(module);
		String result = null;
		try {
			result = map.writeValueAsString(parameters);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public Abacus saveFullAbacus(int id, String name, int contract, String parameters ) {
		Abacus abacus;
		if(id!=0) {
			abacus = abacusServiceImpl.findById(id);
			parameterServiceImpl.deleteParametersFromAbacus(abacus);

		}
		else {
			abacus = new Abacus();
		}
		
		abacus.setName(name);
		abacus.setArchived(false);
		abacus.setContract(contractServiceImpl.findById(contract));
		
		abacusServiceImpl.save(abacus);

		parameterDtoServiceImpl.createModulesAndParameterFromDto(parameterDtoServiceImpl.jsonToDto(parameters), abacus);
		
		return abacus;
	}
	
}

