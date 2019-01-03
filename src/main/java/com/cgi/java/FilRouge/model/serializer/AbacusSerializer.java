package com.cgi.java.FilRouge.model.serializer;

import java.io.IOException;

import com.cgi.java.FilRouge.helper.JGenNull;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Quote;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class AbacusSerializer extends StdSerializer<Abacus>{
	
	public AbacusSerializer() {
		this(null);
	}
	
	public AbacusSerializer(Class<Abacus> T) {
		super(T);
	}

	public void serialize(Abacus abacus, JsonGenerator jgen, SerializerProvider provider) 
			throws IOException, JsonProcessingException{
				jgen.writeStartObject();
				
				jgen.writeNumberField("id", abacus.getId());
				jgen.writeStringField("name", abacus.getName());
				jgen.writeBooleanField("isArchived",abacus.isArchived());
				JGenNull.writeDateField(jgen, "createdOn",abacus.getCreatedOn());
				
				jgen.writeEndObject();
				
				//	obj getChargesVentilationTable
				
				
	}
				
}


