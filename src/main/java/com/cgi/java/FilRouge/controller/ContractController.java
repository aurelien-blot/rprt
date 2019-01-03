package com.cgi.java.FilRouge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.serializer.ContractSerializer;
import com.cgi.java.FilRouge.service.ContractServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


@RestController
@RequestMapping("/test/contract")
public class ContractController {
	
	@Autowired
	ContractServiceImpl contractServiceImpl;

	@RequestMapping("/serialize")
	public String serialize() {
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleModule module = new SimpleModule();
		//	On va sérialiser avec ContractSerializer(), on pourrait appeler un autre serializer
		module.addSerializer(Contract.class, new ContractSerializer());
		mapper.registerModule(module);
		
		Contract c = contractServiceImpl.findById(5);
		
		String jsonDataString = "";
		try {
			
			jsonDataString = mapper.writeValueAsString(c);
			
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return jsonDataString;
	}
}
