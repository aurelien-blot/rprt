package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;

import com.cgi.java.FilRouge.model.Team;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TeamSerializer extends StdSerializer<Team> {
	public TeamSerializer() {
		this(null);
	}
	
	public TeamSerializer(Class<Team> T) {
		super(T);
	}

	@Override
	public void serialize(Team team, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException, JsonProcessingException{
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", team.getId());
		jgen.writeStringField("name", team.getName());
		

		jgen.writeEndObject();
		
	}
}
