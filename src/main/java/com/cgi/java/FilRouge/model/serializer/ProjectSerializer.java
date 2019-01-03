package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;

import com.cgi.java.FilRouge.dto.PhaseDto;
import com.cgi.java.FilRouge.model.Project;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ProjectSerializer  extends StdSerializer<Project> {
	public ProjectSerializer() {
		this(null);
	}
	
	public ProjectSerializer(Class<Project> T) {
		super(T);
	}

	@Override
	public void serialize(Project project, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException, JsonProcessingException{
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", project.getId());
		jgen.writeStringField("name", project.getName());
		jgen.writeStringField("shortName", project.getShortName());
		jgen.writeBooleanField("isArchived",project.isArchived());
		

		jgen.writeEndObject();
		
	}
}
