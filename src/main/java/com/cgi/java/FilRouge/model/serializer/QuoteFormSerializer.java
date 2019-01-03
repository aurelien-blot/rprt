package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;
import java.util.List;

import com.cgi.java.FilRouge.dto.QuoteFormDto;
import com.cgi.java.FilRouge.helper.JGenNull;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class QuoteFormSerializer extends StdSerializer<QuoteFormDto>{
	public QuoteFormSerializer() {
		this(null);
	}
	
	public QuoteFormSerializer(Class<QuoteFormDto> T) {
		super(T);
	}
	
	@Override
	public void serialize(QuoteFormDto quoteForm, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException, JsonProcessingException{
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", quoteForm.getId());
		jgen.writeStringField("name", quoteForm.getName());
		
		//obj phases
		
		jgen.writeFieldName("phases");
		jgen.writeStartArray();
		for(Phase pha : quoteForm.getPhases()) {
			//	Phase est une classe abstraite
			//	Chacune des classes dérivées implémenteront .serialize()
			pha.serialize(jgen);
		}
		//obj modules
		
		//	obj getChargesVentilationTable
		
		jgen.writeStringField("description", quoteForm.getDescription());
		jgen.writeStringField("code", quoteForm.getCode());
		
			
		//	obj getProject
		if( JGenNull.writeStartObject(jgen, "project", quoteForm.getProject()) ){
			Project project = quoteForm.getProject();
			jgen.writeNumberField("id", project.getId());
			jgen.writeStringField("name", project.getName());
			JGenNull.writeDateField(jgen, "creationDate", project.getCreationDate());
			jgen.writeBooleanField("archived", project.isArchived());
			jgen.writeEndObject();
		}
			
			
		//	obj metricTable
		if( JGenNull.writeStartObject(jgen, "metricTable", quoteForm.getAbacus()) ){
			
			Abacus mt = quoteForm.getAbacus();
			jgen.writeNumberField("id", mt.getId());
			jgen.writeStringField("name", mt.getName());
			JGenNull.writeDateField(jgen, "createdOn", mt.getCreatedOn());
			jgen.writeBooleanField("archived", mt.isArchived());

			jgen.writeEndObject();
		}
		
		jgen.writeEndObject();

	}
}
