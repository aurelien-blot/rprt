package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;

import com.cgi.java.FilRouge.helper.JGenNull;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Metric;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class MetricSerializer extends StdSerializer<Metric>{
	
	public MetricSerializer() {
		this(null);
	}
	
	public MetricSerializer(Class<Metric> T) {
		super(T);
	}

	public void serialize(Metric metric, JsonGenerator jgen, SerializerProvider provider) 
			throws IOException, JsonProcessingException{
				jgen.writeStartObject();
				
				jgen.writeNumberField("id", metric.getId());
				jgen.writeStringField("name", metric.getComplexity());
				jgen.writeStringField("name", metric.getInterventionLevel());
				jgen.writeStringField("name", metric.getTask());
				jgen.writeStringField("name", metric.getTypology());
				jgen.writeStringField("name", metric.getType());
				
				jgen.writeEndObject();
				
				//	obj getChargesVentilationTable
				
				
	}
				
}


