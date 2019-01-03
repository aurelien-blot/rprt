package com.cgi.java.FilRouge.service.dto;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.beanutils.BeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.dto.ModuleDto;
import com.cgi.java.FilRouge.dto.PhaseDto;
import com.cgi.java.FilRouge.dto.QuoteFormDto;
import com.cgi.java.FilRouge.dto.mapper.ModuleDtoMapper;
import com.cgi.java.FilRouge.dto.mapper.PhaseDtoMapper;
import com.cgi.java.FilRouge.dto.mapper.QuoteDtoMapper;
import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.cgi.java.FilRouge.model.Module;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.PhaseRtu;
import com.cgi.java.FilRouge.model.serializer.PhaseSerializer;
import com.cgi.java.FilRouge.model.serializer.QuoteFormSerializer;
import com.cgi.java.FilRouge.service.ModuleServiceImpl;
import com.cgi.java.FilRouge.service.ParameterServiceImpl;
import com.cgi.java.FilRouge.service.PhaseDtoServiceImpl;
import com.cgi.java.FilRouge.service.PhaseRtuServiceImpl;
import com.cgi.java.FilRouge.service.PhaseServiceImpl;
import com.cgi.java.FilRouge.service.QuoteServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


@Service
public class QuoteFormServiceImpl {
	
	@Autowired
	QuoteServiceImpl quoteServiceImpl;
	@Autowired
	PhaseServiceImpl phaseServiceImpl;
	
	@Autowired
	PhaseDtoMapper phaseDtoMapper;

	@Autowired
	PhaseDtoServiceImpl phaseDtoServiceImpl;
	
	@Autowired
	ModuleServiceImpl moduleServiceImpl;
	
	@Autowired
	ModuleDtoMapper moduleDtoMapper;
	
	@Autowired
	PhaseRtuServiceImpl phaseRtuServiceImpl;
	
	@Autowired
	ParameterServiceImpl parameterServiceImpl;
	
	@Autowired
	public QuoteFormServiceImpl() {
		
	}
	
	public QuoteFormDto modifyDtoRequest(QuoteFormDto quoteFormIn, String quoteName, String quoteDescription, String phases, String modules, String deletedPhases, String deletedModules) {
		ObjectMapper mapper = new ObjectMapper();
		PhaseRtu rtu=null;
		for(Phase phase:quoteFormIn.getPhases()) {
			if (phase.getPhaseType()==EnumPhaseType.RTU) {
				rtu = (PhaseRtu)phase;
			}
		}
		//PhaseRtu rtu = quoteServiceImpl.findById(quoteFormIn.getId()).getPhaseRtu();
		// ON ENREGISTRE LES NOUVEAUX MODULES

		List<ModuleDto> modulesDtoList = new ArrayList<ModuleDto>();
		
		String modulesJson = modules;
		try {
			modulesDtoList = mapper.readValue(modulesJson,  new TypeReference<List<ModuleDto>>(){});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		quoteFormIn.setModules(new ArrayList<Module>());
		for(ModuleDto moduleDto: modulesDtoList) {
			
			moduleDto.setParameter(parameterServiceImpl.findById(moduleDto.getParameterId()));
			Module module= moduleDtoMapper.toEntity(moduleDto);
			module.setPhaseRtu(rtu);
			moduleServiceImpl.save(module);
			quoteFormIn.getModules().add(module);
		}
		
		
		// ON SUPPRIME LES MODULES EFFACEES
		List<Integer> listDeletedModulesDto= new ArrayList<Integer>();
	
		String deletedModulesJson = deletedModules;
		try {
			listDeletedModulesDto = mapper.readValue(deletedModulesJson,  new TypeReference<ArrayList<Integer>>(){});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int id: listDeletedModulesDto) {
			
			moduleServiceImpl.delete(id);
			for(int i=0; i<quoteFormIn.getModules().size();i++) {
				if(quoteFormIn.getModules().get(i).getId()==id) {
					quoteFormIn.getModules().remove(i);
				}
			}
			
		}
		// ON ENREGISTRE LES NOUVELLES PHASES

		List<PhaseDto> listPhasesDto= new ArrayList<PhaseDto>();

		
		String phasesJson = phases;
		
		try {
			listPhasesDto = mapper.readValue(phasesJson,  new TypeReference<List<PhaseDto>>(){});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		quoteFormIn.setPhases(new ArrayList<Phase>());
		for(PhaseDto phaseDto: listPhasesDto) {
			//phaseDtoServiceImpl.attributeClass(phaseDto);
			Phase phase = phaseDtoMapper.toEntity(phaseDto);
			phase.setQuote(quoteServiceImpl.findById(quoteFormIn.getId()));
			if(phase instanceof PhaseRtu) {

				((PhaseRtu) phase).setModules(quoteFormIn.getModules());
				((PhaseRtu) phase).setValue(phaseRtuServiceImpl.calculTotalRtu((PhaseRtu) phase));
			}
			quoteFormIn.getPhases().add(phase);
			phaseServiceImpl.save(phase);		
		}
		// ON SUPPRIME LES PHASES EFFACEES DANS LA BASE ET DANS LE DTO
		
		List<Integer> listDeletedPhasesDto= new ArrayList<Integer>();
	
		String deletedPhasesJson = deletedPhases;
		try {
			listDeletedPhasesDto = mapper.readValue(deletedPhasesJson,  new TypeReference<ArrayList<Integer>>(){});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int id: listDeletedPhasesDto) {
			phaseServiceImpl.delete(id);
			for(int i=0; i<quoteFormIn.getPhases().size();i++) {
				if(quoteFormIn.getPhases().get(i).getId()==id) {
					quoteFormIn.getPhases().remove(i);
				}
			}
		}
		
		QuoteFormDto quoteFormOut = quoteFormIn;
		quoteFormOut.setName(quoteName);
		quoteFormOut.setDescription(quoteDescription);
		return quoteFormOut;
	}
	
}
