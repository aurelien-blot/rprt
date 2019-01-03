package com.cgi.java.FilRouge.enums;
 
 public enum EnumPhaseType {
	RTU("Réalisation et Tests Unitaires"), ONRTU("Sur RTU"), WITHOUTRATIO("Sans Ratio"), UOS("Unité d'oeuvre spécifique"), ONGLOBAL("Sur Global");
 	
	private final String value;
 
	private EnumPhaseType(final String value) {
       this.value = value;
	}

    public String getValue() { return value; }
 }

