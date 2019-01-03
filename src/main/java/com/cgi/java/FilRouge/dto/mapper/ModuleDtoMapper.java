package com.cgi.java.FilRouge.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.java.FilRouge.dto.ModuleDto;
import com.cgi.java.FilRouge.dto.QuoteFormDto;
import com.cgi.java.FilRouge.model.Module;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.service.ParameterServiceImpl;

@Component
public class ModuleDtoMapper {

private final ModelMapper mapper;
	

	public ModuleDtoMapper() {
	    this.mapper= new ModelMapper();	 
	    
	}

    
    public ModuleDto toDTO(Module entity){
 	    
    	return mapper.map(entity, ModuleDto.class);
    	
    }
    public Module toEntity(ModuleDto dto){
    	
    	return mapper.map(dto, Module.class);
    }

    /* ... */
}