package com.cgi.java.FilRouge.service.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.dto.ParameterDto;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Metric;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.service.MetricServiceImpl;
import com.cgi.java.FilRouge.service.ParameterServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ParameterDtoServiceImpl {
	
	@Autowired
	MetricServiceImpl metricServiceImpl;
	
	@Autowired
	ParameterServiceImpl parameterServiceImpl;


	public List<ParameterDto> jsonToDto(String parameters){
			
		
			ObjectMapper map = new ObjectMapper();
			List<ParameterDto> result = null;
	
			try {
				result = map.readValue(parameters,  new TypeReference<List<ParameterDto>>(){});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return result;
		}
	
	public void createModulesAndParameterFromDto(List<ParameterDto> parametersDto, Abacus abacus) {

		for(ParameterDto parameterDto : parametersDto) {
			
			Metric metric = metricServiceImpl.generateAndSaveMetricIfDontExist(parameterDto.getType(), parameterDto.getTypology(), parameterDto.getTask(), parameterDto.getInterventionLevel(), parameterDto.getComplexity());
			Parameter parameter = new Parameter(abacus, metric,parameterDto.getUnitCharge());
			
			parameterServiceImpl.save(parameter);
		}
		
	}
	
	
}
