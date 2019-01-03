package com.cgi.java.FilRouge.enums;

public enum EnumStatus {

	INPROGRESS("En cours"), WAITINGVALIDATION("En attente de validation"), VALIDATED("Validé"), REFUSED("Refusé"), ARCHIVED("Archivé");

	private final String value;

    private EnumStatus(final String value) {
        this.value = value;
    }

    public String getValue() { return value; }
}
