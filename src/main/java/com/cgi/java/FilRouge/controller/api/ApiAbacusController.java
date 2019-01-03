package com.cgi.java.FilRouge.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgi.java.FilRouge.dto.QuoteFormDto;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.model.serializer.AdminParameterSerializer;
import com.cgi.java.FilRouge.model.serializer.ParameterSerializer;
import com.cgi.java.FilRouge.model.serializer.ProjectSerializer;
import com.cgi.java.FilRouge.service.AbacusServiceImpl;
import com.cgi.java.FilRouge.service.ContractServiceImpl;
import com.cgi.java.FilRouge.service.ParameterServiceImpl;
import com.cgi.java.FilRouge.service.UserServiceImpl;
import com.cgi.java.FilRouge.service.dto.ParameterDtoServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


@RestController
@RequestMapping("/administration/abacii")
public class ApiAbacusController {
	
	
	@Autowired
	AbacusServiceImpl abacusServiceImpl;
	
	@Autowired
	ContractServiceImpl contractServiceImpl;
	
	@Autowired
	ParameterDtoServiceImpl parameterDtoServiceImpl;
	
	@Autowired
	ParameterServiceImpl parameterServiceImpl;
	
	
	@PostMapping(value={"/save"})
	public String saveAbacus (@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("contract") int contract, @RequestParam("parameters") String parameters) {

		
			Abacus abacus= abacusServiceImpl.saveFullAbacus(id, name, contract, parameters );

			
			return "{\"message\" : \"ok\", \"id\":"+abacus.getId()+"}" ;
	   
	}
	
	@PostMapping(value={"/finalize"})
	public String finalizeAbacus (@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("contract") int contract, @RequestParam("parameters") String parameters) {

		
			Abacus abacus= abacusServiceImpl.saveFullAbacus(id, name, contract, parameters );
			abacus.setFinished(true);
			abacusServiceImpl.save(abacus);
			
			return "{\"message\" : \"ok\", \"id\":"+abacus.getId()+"}" ;
	   
	}
	
	
	
	@GetMapping(value={"/loadtemplate/{abacusId}"}, produces = "application/json")
	public String loadAbacus (@PathVariable("abacusId") int abacusId,  Model model) {

			Abacus abacus = abacusServiceImpl.findById(abacusId);
			
			String result = abacusServiceImpl.serializeToCreateAbacus(abacus);
			
			return result;
	   
	}
	
}
