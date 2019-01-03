package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;
import java.util.List;

import com.cgi.java.FilRouge.helper.JGenNull;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Parameter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class AdminParameterSerializer  extends StdSerializer<Parameter>{

	public AdminParameterSerializer() {
		this(null);
	}
	
	public AdminParameterSerializer(Class<Parameter> T) {
		super(T);
	}

	public void serialize(Parameter parameter, JsonGenerator jgen, SerializerProvider provider) 
			throws IOException, JsonProcessingException{
				jgen.writeStartObject();

				jgen.writeStringField("type", parameter.getMetric().getType());
				jgen.writeStringField("typology", parameter.getMetric().getTypology());
				jgen.writeStringField("complexity", parameter.getMetric().getComplexity());
				jgen.writeStringField("interventionLevel", parameter.getMetric().getInterventionLevel());
				jgen.writeStringField("task", parameter.getMetric().getTask());
				jgen.writeNumberField("unitCharge", parameter.getUnitCharge());

				
				jgen.writeEndObject();
				
				//	obj getChargesVentilationTable
				
				
	}
				
}


