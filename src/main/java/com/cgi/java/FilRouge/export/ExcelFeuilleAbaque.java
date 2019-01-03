package com.cgi.java.FilRouge.export;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.cgi.java.FilRouge.model.Parameter;


public class ExcelFeuilleAbaque {

	private String[] colParametre = {"Type", "Rôle", "Typologie", "Complexité", "Niveau d'intervention", "Charge unitaire"};
	
    //Liste de parametres pour la premiere feuille
    private static List<Parameter> listeParametres =  new ArrayList<Parameter>();
	
	public void creationFeuilleAbaque(XSSFWorkbook workbook, List<Parameter> listeParametresRecu) throws IOException{		  
		
		listeParametres = listeParametresRecu;
		
		//Création feuille Abaque
		XSSFSheet sheetParametres = workbook.createSheet("Abaque");
		 
		        
	/*****************************************************/
	/*** Création police et style de la feuille Abaque ***/
	/*****************************************************/      
		        
		/*** Header Abaque ***/
		        
		// Création d'une police pour le header "Abaque"
		Font headerFontAbaque = workbook.createFont();
		headerFontAbaque.setBold(true);
		headerFontAbaque.setFontHeightInPoints((short) 12);
		headerFontAbaque.setColor(IndexedColors.BLACK.getIndex());

		// Création du style pour le header "Abaque"
		XSSFCellStyle headerCellStyleAbaque = workbook.createCellStyle();
		headerCellStyleAbaque.setFont(headerFontAbaque);
		headerCellStyleAbaque.setBorderTop(BorderStyle.THIN);
		headerCellStyleAbaque.setBorderRight(BorderStyle.THIN);
		headerCellStyleAbaque.setBorderLeft(BorderStyle.THIN);
		headerCellStyleAbaque.setBorderBottom(BorderStyle.THIN);
		
	/**********************************************************/
	/*** Création de la mise en page de la feuille "Abaque" ***/
	/**********************************************************/ 	
		
		Row headerRowParametres = sheetParametres.createRow(0);
        
        for(int i = 0; i < colParametre.length; i++) {
            Cell cell = headerRowParametres.createCell(i);
            cell.setCellValue(colParametre[i]);
            cell.setCellStyle(headerCellStyleAbaque);
        }
		
        
     /*****************************************/
     /*** Alimentation de la feuille Abaque ***/
     /*****************************************/      
        
        int rowNum = 1;
        
        for(Parameter parametre: listeParametres) {
            Row row = sheetParametres.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(parametre.getMetric().getType());

            row.createCell(1)
                    .setCellValue(parametre.getMetric().getTask());
            
            row.createCell(2)
            		.setCellValue(parametre.getMetric().getTypology());
            
            row.createCell(3)
            		.setCellValue(parametre.getMetric().getComplexity());
            
            row.createCell(4)
            		.setCellValue(parametre.getMetric().getInterventionLevel());
            
            row.createCell(5)
            		.setCellValue(parametre.getUnitCharge());

        }
        
		
        /*** Ajustement de la taille des colonnes en fonctions des données **/
        
        for(int i = 0; i <= colParametre.length; i++) {
            sheetParametres.autoSizeColumn(i);
        }
        
        
        
	 }

	
}
