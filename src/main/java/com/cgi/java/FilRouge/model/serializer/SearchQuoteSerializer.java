package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;

import com.cgi.java.FilRouge.model.Quote;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class SearchQuoteSerializer extends StdSerializer<Quote>{
	
	public SearchQuoteSerializer() {
		this(null);
	}
	
	public SearchQuoteSerializer(Class<Quote> T) {
		super(T);
	}
	
	@Override
	public void serialize(Quote value, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException, JsonProcessingException{
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("name", value.getName());
		
		jgen.writeEndObject();
		
	}
}
