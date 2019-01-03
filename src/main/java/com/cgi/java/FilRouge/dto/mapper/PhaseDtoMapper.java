package com.cgi.java.FilRouge.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.java.FilRouge.dto.PhaseDto;
import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.PhaseRtu;
import com.cgi.java.FilRouge.model.Quote;
@Component
public class PhaseDtoMapper {
	
	
	private final ModelMapper mapper;
	
	
	public PhaseDtoMapper() {
	    this.mapper= new ModelMapper();	    
	    
	}

    
    public PhaseDto toDTO(Phase entity){
 	    
    	return mapper.map(entity, PhaseDto.class);
    	
    }
    public Phase toEntity(PhaseDto dto){
    	
    	if(dto.getPhaseType()==EnumPhaseType.RTU) {
    		Object castPhaseRtu = mapper.map(dto, PhaseRtu.class);
    		Phase phaseRtu = (Phase)castPhaseRtu;
    		return phaseRtu;
    	}
    	else {
    		return mapper.map(dto, Phase.class);
    	}
    		
    	/*	
    	try {

        	Object testCast = mapper.map(dto, Class.forName(dto.getType()));

        	
			return (Phase)testCast;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }

    /* ... */
}