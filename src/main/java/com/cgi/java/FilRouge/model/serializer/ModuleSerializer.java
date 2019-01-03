package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.java.FilRouge.dto.ModuleDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ModuleSerializer  extends StdSerializer<ModuleDto> {

	@Autowired
	ObjectMapper objectMapper;
	
	
	public ModuleSerializer() {
		this(null);
	}
	
	public ModuleSerializer(Class<ModuleDto> T) {
		super(T);
	}

	@Override
	public void serialize(ModuleDto moduleDto, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException, JsonProcessingException{
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", moduleDto.getId());
		jgen.writeStringField("name", moduleDto.getName());
		jgen.writeNumberField("revisedCharge", moduleDto.getRevisedCharge());
		//jgen.
		
		
		
		jgen.writeEndObject();
		
	}
}


