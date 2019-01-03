package com.cgi.java.FilRouge.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.model.serializer.ContractSerializer;
import com.cgi.java.FilRouge.model.serializer.QuoteSerializer;
import com.cgi.java.FilRouge.service.ProjectSequenceServiceImpl;
import com.cgi.java.FilRouge.service.ProjectServiceImpl;
import com.cgi.java.FilRouge.service.QuoteServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	QuoteServiceImpl quoteServiceImpl;
	
	@Autowired ProjectSequenceServiceImpl pssi;
	@Autowired ProjectServiceImpl psi;
	
	@RequestMapping("/cotcot")
	public String cotcot() {
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleModule module = new SimpleModule();
		//	On va sérialiser avec ContractSerializer(), on pourrait appeler un autre serializer
		module.addSerializer(Quote.class, new QuoteSerializer());
		mapper.registerModule(module);
		
		Quote q = quoteServiceImpl.findById(109);
		
		String jsonDataString = "";
		try {
			
			jsonDataString = mapper.writeValueAsString(q);
			
			Quote q1 = mapper.readValue(jsonDataString, Quote.class);
			
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonDataString);
		
		return jsonDataString;
	}
	
	@RequestMapping("/test")
	public String test() {
		Project p = psi.findById(638);
		return p.getName();
	}
	
	@RequestMapping("/count")
	public String count() {
		Quote q = quoteServiceImpl.findAll().get(0);
		//System.out.println(quoteServiceImpl.generateUniqueCode(q));
		
		return quoteServiceImpl.generateUniqueCode(q);
	}
	
}
