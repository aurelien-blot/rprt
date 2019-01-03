package com.cgi.java.FilRouge.model.serializer;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.java.FilRouge.dto.PhaseDto;
import com.cgi.java.FilRouge.dto.QuoteFormDto;
import com.cgi.java.FilRouge.helper.JGenNull;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.Project;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PhaseSerializer extends StdSerializer<PhaseDto> {

	
	@Autowired
	ObjectMapper objectMapper;
	
	
	public PhaseSerializer() {
		this(null);
	}
	
	public PhaseSerializer(Class<PhaseDto> T) {
		super(T);
	}

	@Override
	public void serialize(PhaseDto phaseDto, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException, JsonProcessingException{
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", phaseDto.getId());
		jgen.writeStringField("name", phaseDto.getName());
		//jgen.writeStringField("type", "com.cgi.java.FilRouge.model.PhaseRtu");
		
		//jgen.writeString("globalWorkload", phaseDto.getChargesVentilationTable());
		//EnumPhaseType phaseType =  phaseTypeServiceImpl.findById(phaseDto.getTypeId());
		//String phaseJson = objectMapper.writeValueAsString(phaseType);
		
		//jgen.writeStringField("phaseType", phaseJson);
		
		//EnumPhaseType phaseType =  phaseDto.getPhaseType();
		//phaseType.serialize(jgen);
		
		
		jgen.writeEndObject();
		
	}
}
