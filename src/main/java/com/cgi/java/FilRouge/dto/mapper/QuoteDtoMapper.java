package com.cgi.java.FilRouge.dto.mapper;

import java.util.ArrayList;
import java.util.List;


import javax.print.attribute.standard.Destination;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.cgi.java.FilRouge.dto.QuoteFormDto;
import com.cgi.java.FilRouge.model.Module;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.PhaseRtu;
import com.cgi.java.FilRouge.model.Quote;
@Component
public class QuoteDtoMapper {
	
	private final ModelMapper mapper;
	
	
	public QuoteDtoMapper() {
		
	    this.mapper= new ModelMapper();

	    this.mapper.addMappings(new PropertyMap<Quote, QuoteFormDto>() {
            @Override
            protected void configure() {
            	map().setPhases(source.getRealPhases());
            	map().setModules(source.getPhaseRtu().getModules());
            }
        });
	    this.mapper.addMappings(new PropertyMap<QuoteFormDto, Quote>() {
            @Override
            protected void configure() {
            	map().setRealPhases(source.getPhases());
            	map().getPhaseRtu().setModules(source.getModules());
            }
        });
	    
	    
	    

	}

    
    public QuoteFormDto toDTO(Quote entity){
 	    
 	  
 	    
    	return mapper.map(entity, QuoteFormDto.class);
    	
    }
    public Quote toEntity(QuoteFormDto dto){
    	
    	return mapper.map(dto, Quote.class);
    }

    /* ... */
}