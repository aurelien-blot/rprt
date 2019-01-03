package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;

import com.cgi.java.FilRouge.model.Project;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class SearchProjectSerializer extends StdSerializer<Project>{
	
	public SearchProjectSerializer() {
		this(null);
	}
	
	public SearchProjectSerializer(Class<Project> T) {
		super(T);
	}
	
	@Override
	public void serialize(Project value, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException, JsonProcessingException{
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("name", value.getName());
		
		jgen.writeEndObject();
		
	}
}

