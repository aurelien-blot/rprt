package com.cgi.java.FilRouge.helper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;


/**
 * Helper pour la génération de JSON avec JsonGenerator
 * JsonGenerator ne vérifie pas si la donnée envoyée est null, causant une NullPointerException
 * */
public class JGenNull {
	
	public static void writeDateField(JsonGenerator jgen, String key, Date date) throws IOException {
		if(date == null) {
			jgen.writeNullField(key);
		}
		else {
			Timestamp ts = new Timestamp(date.getTime());
			jgen.writeNumberField(key, ts.getTime());
		}
	}
	
	public static void writeStringField(JsonGenerator jgen, String key, String value) throws IOException {
		if(value == null) {
			jgen.writeNullField(key);
		}
		else {
			jgen.writeStringField(key, value.toString());
		}
	}
	
	/**
	 * writeNullIfNull vérifie si la valeur est null, sans démarrer d'objet
	 * */
	public static boolean writeNullIfNull(JsonGenerator jgen, String key, Object value) throws IOException {
		if(key != null) {
			jgen.writeFieldName(key);
		}
		if(value == null) {
			jgen.writeNull();
			return false;
		}
		else {
			return true;
		}
	}
	
	
	/**
	 * Écrit le FieldName et un startObject ou un nullField en fonction de la valeur de l'objet
	 * Return un boolean correspondant à l'éxécution de StartObject ou non
	 * */
	public static boolean writeStartObject(JsonGenerator jgen, String key, Object value) throws IOException {
		if(key != null) {
			jgen.writeFieldName(key);
		}
		if(value == null) {
			jgen.writeNull();
			return false;
		}
		else {
			jgen.writeStartObject();
			return true;
		}
	}
	
	//	Si l'objet n'a pas de clé, c'est probablement parce qu'il est dans un Array
	//	Il ne doit pas obtenir de fieldName !
	public static boolean writeStartObject(JsonGenerator jgen, Object value) throws IOException {
		return writeStartObject(jgen, null, value);
	}
}
