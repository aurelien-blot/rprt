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

public class ParameterSerializer  extends StdSerializer<Parameter>{

	public ParameterSerializer() {
		this(null);
	}
	
	public ParameterSerializer(Class<Parameter> T) {
		super(T);
	}

	public void serialize(Parameter parameter, JsonGenerator jgen, SerializerProvider provider) 
			throws IOException, JsonProcessingException{
				jgen.writeStartObject();
				
				jgen.writeNumberField("id", parameter.getId());
				jgen.writeNumberField("unitCharge", parameter.getUnitCharge());
				//jgen.writeObjectField("metric",parameter.getMetric());
				jgen.writeFieldName("metric");
				if(JGenNull.writeStartObject(jgen, parameter.getMetric())) {
						jgen.writeNumberField("id",parameter.getMetric().getId());
						jgen.writeStringField("type", parameter.getMetric().getType());
						jgen.writeStringField("typology", parameter.getMetric().getTypology());
						jgen.writeStringField("complexity", parameter.getMetric().getComplexity());
						jgen.writeStringField("interventionLevel", parameter.getMetric().getInterventionLevel());
						jgen.writeStringField("task", parameter.getMetric().getTask());
						
					jgen.writeEndObject();
				}
				
				
				jgen.writeEndObject();
				
				//	obj getChargesVentilationTable
				
				
	}
				
}


