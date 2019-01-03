package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;

import com.cgi.java.FilRouge.helper.JGenNull;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Team;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


public class ContractSerializer extends StdSerializer<Contract> {

	public ContractSerializer() {
		this(null);
	}
	
	public ContractSerializer(Class<Contract> T) {
		super(T);
	}
	
	@Override
	public void serialize(Contract value, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException, JsonProcessingException{
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("name", value.getName());
		jgen.writeStringField("client", value.getClient());
		
		jgen.writeFieldName("territory");
		if( JGenNull.writeStartObject(jgen, "territory", value.getTerritory()) ) {
			jgen.writeStringField("name", value.getTerritory().getName());
			jgen.writeEndObject();
		}
			
			
		jgen.writeFieldName("teams");
		jgen.writeStartArray();
			for(Team t : value.getTeams()) {
				if(JGenNull.writeStartObject(jgen, t)) {
						jgen.writeNumberField("id", t.getId());
						jgen.writeStringField("name", t.getName());
						jgen.writeFieldName("projects");
						jgen.writeStartArray();
						for(Project project : t.getProjects()) {
							if(JGenNull.writeStartObject(jgen, project)) {
								jgen.writeNumberField("id", project.getId());
								jgen.writeStringField("name", project.getName());
								jgen.writeNumberField("creationDate", project.getCreationDate().getTime());
							jgen.writeEndObject();
							}
						}
						jgen.writeEndArray();
						/*
						jgen.writeFieldName("project");
						if(JGenNull.writeStartObject(jgen, t.getProject())) {
								jgen.writeNumberField("id", t.getProject().getId());
								jgen.writeStringField("name", t.getProject().getName());
								jgen.writeNumberField("creationDate", t.getProject().getCreationDate().getTime());
							jgen.writeEndObject();
						}*/
				
					jgen.writeEndObject();
				}
				
			}
		jgen.writeEndArray();
	
		jgen.writeEndObject();
	}
	

}
