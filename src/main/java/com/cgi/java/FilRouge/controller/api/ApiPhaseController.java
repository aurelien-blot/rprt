package com.cgi.java.FilRouge.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.serializer.PhaseSerializer;
import com.cgi.java.FilRouge.model.serializer.ProjectSerializer;
import com.cgi.java.FilRouge.service.PhaseServiceImpl;
//import com.cgi.java.FilRouge.service.search.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@RestController
@RequestMapping("/api/phase")
public class ApiPhaseController {
	
	@Autowired private PhaseServiceImpl phaseService;
	
	//@Autowired private SearchService searchService;
	
	@GetMapping(value= {"/{q}"}, produces="application/json")
	public String getPhase(@PathVariable("q") String query) {
		List<Phase> phases = phaseService.findDistinctByLikeQuery("%"+query+"%");
		//List<Phase> phases = searchService.phaseSearch(query);
						
		// retourne un tableau compatible Jquery UI Autocomplete
		if(phases.size() > 0) {
			String buffer = "[";
			for( Phase p : phases ) {
				buffer += "\""+ p.getName() +"\",";
			}
			buffer = buffer.substring(0, buffer.length() - 1) + "]";

			return buffer;
		}
		else {
			return "[]";
		}
		
	}
}
