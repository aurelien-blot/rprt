package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;

import com.cgi.java.FilRouge.enums.EnumStatus;
import com.cgi.java.FilRouge.helper.JGenNull;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Amendment;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class QuoteSerializer extends StdSerializer<Quote> {
	public QuoteSerializer() {
		this(null);
	}
	
	public QuoteSerializer(Class<Quote> T) {
		super(T);
	}
	
	@Override
	public void serialize(Quote quote, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException, JsonProcessingException{
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", quote.getId());
		jgen.writeStringField("name", quote.getName());
		//	obj getChargesVentilationTable
		
		jgen.writeFieldName("phases");
		jgen.writeStartArray();
		for(Phase pha : quote.getRealPhases()) {
			//	Phase est une classe abstraite
			//	Chacune des classes dérivées implémenteront .serialize()
			pha.serialize(jgen);
		}
		jgen.writeEndArray();
		/*jgen.writeStartObject();
		
			
			jgen.writeNumberField("id", cvt.getId());
			jgen.writeStringField("label", cvt.getLabel());
		
		
		jgen.writeEndObject();*/
		
		
		
		
		jgen.writeStringField("description", quote.getDescription());
		jgen.writeStringField("code", quote.getCode());
		jgen.writeStringField("status", quote.getStatus().getValue());
		
			
		//	obj getProject
		if( JGenNull.writeStartObject(jgen, "project", quote.getProject()) ){
			Project project = quote.getProject();
			jgen.writeNumberField("id", project.getId());
			jgen.writeStringField("name", project.getName());
			JGenNull.writeDateField(jgen, "creationDate", project.getCreationDate());
			jgen.writeBooleanField("archived", project.isArchived());
			jgen.writeEndObject();
		}
			
			
		//	obj metricTable
		if( JGenNull.writeStartObject(jgen, "metricTable", quote.getAbacus()) ){
			
			Abacus mt = quote.getAbacus();
			jgen.writeNumberField("id", mt.getId());
			jgen.writeStringField("name", mt.getName());
			JGenNull.writeDateField(jgen, "createdOn", mt.getCreatedOn());
			jgen.writeBooleanField("archived", mt.isArchived());
			
			
			//	Répéter deux fois un objet est interdit dans la déserialisation
			//	array obj getMetrics
			/*jgen.writeFieldName("metrics");
			jgen.writeStartArray();
			for(Parameter mc : mt.getMetrics()) {
				jgen.writeStartObject();
					jgen.writeNumberField("id", mc.getId());
					jgen.writeStringField("type", mc.getType());
					jgen.writeStringField("typology", mc.getTypology());
					jgen.writeStringField("task", mc.getTask());
					jgen.writeStringField("complexity", mc.getComplexity());
					jgen.writeStringField("interventionLevel", mc.getInterventionLevel());
					jgen.writeNumberField("unitCharge", mc.getUnitCharge());
				
				jgen.writeEndObject();
			}
			jgen.writeEndArray();*/
			
			
			jgen.writeEndObject();
		}
			
			
			
		
		JGenNull.writeDateField(jgen, "createdOn", quote.getCreatedOn());
		JGenNull.writeDateField(jgen, "updatedOn", quote.getCancelledOn());
		// array obj getAmendments
		/*jgen.writeFieldName("amendments");
			jgen.writeStartArray();
			for(Amendment t : quote.getAmendments()) {
				jgen.writeStartObject();
					jgen.writeNumberField("id", project.getId());
				
				jgen.writeEndObject();
			}
			jgen.writeEndArray();
		*/
		//	obj getCreator (User)
		if( JGenNull.writeStartObject(jgen, "creator", quote.getCreator())) {			
			User creator = quote.getCreator();
			jgen.writeNumberField("id", creator.getId());
			jgen.writeStringField("firstname", creator.getFirstname());
			jgen.writeStringField("lastname", creator.getLastname());
			JGenNull.writeDateField(jgen, "birthdate", creator.getBirthdate());
			jgen.writeStringField("staff_number", creator.getStaffNumber());
			
			jgen.writeStringField("username", creator.getUsername());
			jgen.writeStringField("password", creator.getPassword());
			jgen.writeEndObject();
		}
		
		
		JGenNull.writeDateField(jgen, "validationWaitedOn", quote.getValidationWaitedOn());
		JGenNull.writeDateField(jgen, "rejectedOn", quote.getRejectedOn());
		JGenNull.writeDateField(jgen, "cancelledOn", quote.getCancelledOn());
		JGenNull.writeDateField(jgen, "validatedOn", quote.getValidatedOn());
		JGenNull.writeDateField(jgen, "archivedOn", quote.getArchivedOn());
		
		//	obj getValidator (User)
		if( JGenNull.writeNullIfNull(jgen, "validator", quote.getValidator())) {	
			jgen.writeStartObject();
				
				User validator = quote.getValidator();
				if( validator.getId() == quote.getValidator().getId() ) {
					jgen.writeNumber(validator.getId());
				}
				else { 
					jgen.writeNumberField("id", validator.getId());
					jgen.writeStringField("firstname", validator.getFirstname());
					jgen.writeStringField("lastname", validator.getLastname());
					JGenNull.writeDateField(jgen, "birthdate", validator.getBirthdate());
					jgen.writeStringField("staff_number", validator.getStaffNumber());
					
					jgen.writeStringField("username", validator.getUsername());
					jgen.writeStringField("password", validator.getPassword());
				}
				
			jgen.writeEndObject();
		}
		
		jgen.writeEndObject();
		
		//jgen.writeStringField("client", value.getClient());
		
	}
}
