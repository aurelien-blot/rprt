package com.cgi.java.FilRouge.export;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cgi.java.FilRouge.model.Module;


public class ExcelFeuilleRealisation {
	
	private String[] colRealisation = {"Nom du module", "Type de module", "Typologie", "Rôle", "Complexité", "Niveau d'intervention", "Charges unitaires", "Charges révisées"};

	//Liste de modules pour la deuxieme feuille
    private static List<Module> listeModules = new ArrayList<Module>();
    
  
    //Calcul pour le total RTU 
  	public double calculTotalRtu(List<Module> listeModules) {
  		
  		double totalRtu = 0;
  			
  		//Calcul pour le total RTU (Total de l'étape 2)
  		for (Module module : listeModules) {
  			
  			//revised_charge en base ne devrait pas pouvoir être à null
  			if(module.getRevisedCharge() == 0) {
  			
  				totalRtu += module.getParameter().getUnitCharge();
  				
  			}else{
  				
  				totalRtu += module.getRevisedCharge();
  			}
  		}
  		
  		return totalRtu;
  	}
    
    
	
	public void creationFeuilleRealisation(XSSFWorkbook workbook, List<Module> listeModulesRecu, String quoteName) throws IOException{
		 
		listeModules = listeModulesRecu;
		
		//Création feuille Chiffrage Réalisation 
		XSSFSheet sheetModules = workbook.createSheet("Chiffrage Réalisation");
		 
		//Désactive le quadrillage 
        sheetModules.setDisplayGridlines(false); 
		 
        
    /********************************************************************/
    /*** Création police et style de la feuille Chiffrage Réalisation ***/
    /********************************************************************/ 
           
        /*** Header Chiffrage Réalisation ***/
           
        /*** Création style pour le titre ***/
           
        // Création d'une police pour le titre "Chiffrage Réalisation"
        Font headerFontModuleTitle = workbook.createFont();
        headerFontModuleTitle.setBold(true);
        headerFontModuleTitle.setItalic(true);
        headerFontModuleTitle.setFontHeightInPoints((short) 13);
        headerFontModuleTitle.setColor(IndexedColors.BLACK.getIndex());

        // Création des styles pour les cellules du titre (droite) "Chiffrage Réalisation"
        XSSFCellStyle headerCellStyleModuleTitleRight = workbook.createCellStyle();
        headerCellStyleModuleTitleRight.setFont(headerFontModuleTitle);
        headerCellStyleModuleTitleRight.setBorderTop(BorderStyle.DOUBLE);
        headerCellStyleModuleTitleRight.setBorderLeft(BorderStyle.DOUBLE);
        headerCellStyleModuleTitleRight.setBorderBottom(BorderStyle.DOUBLE);
        headerCellStyleModuleTitleRight.setAlignment(HorizontalAlignment.CENTER);
           
        // Création des styles pour les cellules du titre (centre) "Chiffrage Réalisation"
        XSSFCellStyle headerCellStyleModuleTitleCenter = workbook.createCellStyle();
        headerCellStyleModuleTitleCenter.setFont(headerFontModuleTitle);
        headerCellStyleModuleTitleCenter.setBorderTop(BorderStyle.DOUBLE);
        headerCellStyleModuleTitleCenter.setBorderBottom(BorderStyle.DOUBLE);
           
        // Création des styles pour les cellules du titre (gauche) "Chiffrage Réalisation"
        XSSFCellStyle headerCellStyleModuleTitleLeft = workbook.createCellStyle();
        headerCellStyleModuleTitleLeft.setFont(headerFontModuleTitle);
        headerCellStyleModuleTitleLeft.setBorderTop(BorderStyle.DOUBLE);
        headerCellStyleModuleTitleLeft.setBorderRight(BorderStyle.DOUBLE);
        headerCellStyleModuleTitleLeft.setBorderBottom(BorderStyle.DOUBLE);
      
        /*** Création du titre ***/
          
        Row headerRowModulesTitle = sheetModules.createRow(1);

        //Creation du titre de la page "Chiffrage Réalisation"
        Cell cellModuleTitleRight = headerRowModulesTitle.createCell(1);
        cellModuleTitleRight.setCellValue("FEM " + quoteName);
        cellModuleTitleRight.setCellStyle(headerCellStyleModuleTitleRight);
           
        Cell cellModuleTitleCenter1 = headerRowModulesTitle.createCell(2);
        cellModuleTitleCenter1.setCellStyle(headerCellStyleModuleTitleCenter);
           
        Cell cellModuleTitleCenter2 = headerRowModulesTitle.createCell(3);
        cellModuleTitleCenter2.setCellStyle(headerCellStyleModuleTitleCenter);
           
        Cell cellModuleTitleCenter3 = headerRowModulesTitle.createCell(4);
        cellModuleTitleCenter3.setCellStyle(headerCellStyleModuleTitleCenter);
           
        Cell cellModuleTitleLeft = headerRowModulesTitle.createCell(5);
        cellModuleTitleLeft.setCellStyle(headerCellStyleModuleTitleLeft);
           
        //Fusion des cellules
        sheetModules.addMergedRegion(new CellRangeAddress(1,1,1,5));
           
        /*** Création du style pour le tableau récapitulatif ***/
           
        //Création d'une police pour la première partie du tableau récapitulatif de la feuille "Chiffrage Réalisation"
        Font fontModuleFirstTab = workbook.createFont();
        fontModuleFirstTab.setFontHeightInPoints((short) 10);
        fontModuleFirstTab.setBold(true);
        fontModuleFirstTab.setColor(IndexedColors.RED.getIndex());
           
        //Création d'une police pour la seconde partie du tableau récapitulatif de la feuille "Chiffrage Réalisation"
        Font fontModuleSecondTab = workbook.createFont();
        fontModuleSecondTab.setFontHeightInPoints((short) 10);
        fontModuleSecondTab.setBold(true);
        fontModuleSecondTab.setColor(IndexedColors.WHITE.getIndex());

        //Création du style pour la première partie supérieure du tableau récapitulatif de la feuille "Chiffrage Réalisation" 
        XSSFCellStyle cellStyleModuleFirstTabSup = workbook.createCellStyle();      
        cellStyleModuleFirstTabSup.setFont(fontModuleFirstTab);
        cellStyleModuleFirstTabSup.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        cellStyleModuleFirstTabSup.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleModuleFirstTabSup.setBorderTop(BorderStyle.MEDIUM);
        cellStyleModuleFirstTabSup.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleModuleFirstTabSup.setBorderRight(BorderStyle.MEDIUM);
        cellStyleModuleFirstTabSup.setBorderBottom(BorderStyle.THIN);
        cellStyleModuleFirstTabSup.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleFirstTabSup.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Création du style pour la première partie inférieure du tableau récapitulatif de la feuille "Chiffrage Réalisation" 
        XSSFCellStyle cellStyleModuleFirstTabInf = workbook.createCellStyle();      
        cellStyleModuleFirstTabInf.setFont(fontModuleFirstTab);
        cellStyleModuleFirstTabInf.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        cellStyleModuleFirstTabInf.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleModuleFirstTabInf.setBorderTop(BorderStyle.THIN);
        cellStyleModuleFirstTabInf.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleModuleFirstTabInf.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleModuleFirstTabInf.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleModuleFirstTabInf.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleFirstTabInf.setVerticalAlignment(VerticalAlignment.CENTER);
           
        //Création du style pour la seconde partie supérieure du tableau récapitulatif de la feuille "Chiffrage Réalisation"
        XSSFCellStyle cellStyleModuleSecondTabSup = workbook.createCellStyle();      
        cellStyleModuleSecondTabSup.setFont(fontModuleSecondTab);
        cellStyleModuleSecondTabSup.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleModuleSecondTabSup.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleModuleSecondTabSup.setBorderTop(BorderStyle.MEDIUM);
        cellStyleModuleSecondTabSup.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleModuleSecondTabSup.setBorderRight(BorderStyle.MEDIUM);
        cellStyleModuleSecondTabSup.setBorderBottom(BorderStyle.THIN);
        cellStyleModuleSecondTabSup.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleSecondTabSup.setVerticalAlignment(VerticalAlignment.CENTER);
         
        //Création du style pour la seconde partie inférieure du tableau récapitulatif de la feuille "Chiffrage Réalisation"
        XSSFCellStyle cellStyleModuleSecondTabInf = workbook.createCellStyle();      
        cellStyleModuleSecondTabInf.setFont(fontModuleSecondTab);
        cellStyleModuleSecondTabInf.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleModuleSecondTabInf.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleModuleSecondTabInf.setBorderTop(BorderStyle.THIN);
        cellStyleModuleSecondTabInf.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleModuleSecondTabInf.setBorderRight(BorderStyle.MEDIUM);
        cellStyleModuleSecondTabInf.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleModuleSecondTabInf.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleSecondTabInf.setVerticalAlignment(VerticalAlignment.CENTER);
           
           
        /*** Création du tableau récapitulatif ***/
          
        Row rowModulesTabFirst = sheetModules.createRow(3);
        Row rowModulesTabSecond = sheetModules.createRow(4);
           
        //Creation du tableau récapitulatif de la page "Chiffrage Réalisation"
        Cell cellModuleFirstTabSup = rowModulesTabFirst.createCell(3);
        cellModuleFirstTabSup.setCellValue("Nombre d'objets créés ou modifiés");
        cellModuleFirstTabSup.setCellStyle(cellStyleModuleFirstTabSup);
           
        Cell cellModuleFirstTabInf = rowModulesTabSecond.createCell(3);
        cellModuleFirstTabInf.setCellValue("Charge Totale de réalisation et TU");
        cellModuleFirstTabInf.setCellStyle(cellStyleModuleFirstTabInf);
          
        Cell cellModuleSecondTabSup = rowModulesTabFirst.createCell(4);
        cellModuleSecondTabSup.setCellValue(listeModules.size());
        cellModuleSecondTabSup.setCellStyle(cellStyleModuleSecondTabSup);
           
        Cell cellModuleSecondTabInf = rowModulesTabSecond.createCell(4);
        cellModuleSecondTabInf.setCellValue(calculTotalRtu(listeModules));
        cellModuleSecondTabInf.setCellStyle(cellStyleModuleSecondTabInf);
           
           
        /*** Création des colonnes ***/
         
        //Création d'une police pour les colonnes de la feuille "Chiffrage Réalisation"
        Font fontModuleColonne = workbook.createFont();
        fontModuleColonne.setFontHeightInPoints((short) 9);
        fontModuleColonne.setBold(true);
        fontModuleColonne.setColor(IndexedColors.DARK_BLUE.getIndex());
           
        //Création du style pour la première cellule des colonnes de la feuille "Chiffrage Réalisation" 
        XSSFCellStyle cellStyleModuleFirstCol = workbook.createCellStyle();      
        cellStyleModuleFirstCol.setFont(fontModuleColonne);
        cellStyleModuleFirstCol.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyleModuleFirstCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleModuleFirstCol.setBorderTop(BorderStyle.MEDIUM);
        cellStyleModuleFirstCol.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleModuleFirstCol.setBorderRight(BorderStyle.THIN);
        cellStyleModuleFirstCol.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleModuleFirstCol.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleFirstCol.setVerticalAlignment(VerticalAlignment.CENTER);
           
        //Création du style pour les cellules des colonnes de la feuille "Chiffrage Réalisation" 
        XSSFCellStyle cellStyleModuleCol = workbook.createCellStyle();      
        cellStyleModuleCol.setFont(fontModuleColonne);
        cellStyleModuleCol.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyleModuleCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleModuleCol.setBorderTop(BorderStyle.MEDIUM);
        cellStyleModuleCol.setBorderLeft(BorderStyle.THIN);
        cellStyleModuleCol.setBorderRight(BorderStyle.THIN);
        cellStyleModuleCol.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleModuleCol.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleCol.setVerticalAlignment(VerticalAlignment.CENTER);
         
        //Création du style pour la première cellule des colonnes de la feuille "Chiffrage Réalisation" 
        XSSFCellStyle cellStyleModuleLastCol = workbook.createCellStyle();      
        cellStyleModuleLastCol.setFont(fontModuleColonne);
        cellStyleModuleLastCol.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyleModuleLastCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleModuleLastCol.setBorderTop(BorderStyle.MEDIUM);
        cellStyleModuleLastCol.setBorderLeft(BorderStyle.THIN);
        cellStyleModuleLastCol.setBorderRight(BorderStyle.MEDIUM);
        cellStyleModuleLastCol.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleModuleLastCol.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleLastCol.setVerticalAlignment(VerticalAlignment.CENTER);
           
        /*** Création des cellules pour les données ***/
           
        //Création d'une police pour les colonnes de la feuille "Chiffrage Réalisation"
        Font fontModuleCell = workbook.createFont();
        fontModuleCell.setFontHeightInPoints((short) 9);
        fontModuleCell.setColor(IndexedColors.BLACK.getIndex());
           
        //Création d'une police pour les colonnes "Charges" de la feuille "Chiffrage Réalisation"
        Font fontModuleCellCharge = workbook.createFont();
        fontModuleCellCharge.setFontHeightInPoints((short) 9);
        fontModuleCellCharge.setBold(true);
        fontModuleCellCharge.setColor(IndexedColors.BLACK.getIndex());
           
           
        //Création du style pour la première cellule pour les données de la feuille "Chiffrage Réalisation" 
        XSSFCellStyle cellStyleModuleFirstCell = workbook.createCellStyle();      
        cellStyleModuleFirstCell.setFont(fontModuleCell);
        cellStyleModuleFirstCell.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleModuleFirstCell.setBorderRight(BorderStyle.THIN);
        cellStyleModuleFirstCell.setBorderBottom(BorderStyle.THIN);
        cellStyleModuleFirstCell.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleFirstCell.setVerticalAlignment(VerticalAlignment.CENTER);
           
        //Création du style pour les cellules pour les données de la feuille "Chiffrage Réalisation" 
        XSSFCellStyle cellStyleModuleCell = workbook.createCellStyle();      
        cellStyleModuleCell.setFont(fontModuleCell);
        cellStyleModuleCell.setBorderLeft(BorderStyle.THIN);
        cellStyleModuleCell.setBorderRight(BorderStyle.THIN);
        cellStyleModuleCell.setBorderBottom(BorderStyle.THIN);
           
        cellStyleModuleCell.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleCell.setVerticalAlignment(VerticalAlignment.CENTER);
           
        //Création du style pour l'avant dernière cellule pour les données de la feuille "Chiffrage Réalisation" 
        XSSFCellStyle cellStyleModuleBeforeLastCell = workbook.createCellStyle();      
        cellStyleModuleBeforeLastCell.setFont(fontModuleCellCharge);
        cellStyleModuleBeforeLastCell.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        cellStyleModuleBeforeLastCell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleModuleBeforeLastCell.setBorderLeft(BorderStyle.THIN);
        cellStyleModuleBeforeLastCell.setBorderRight(BorderStyle.THIN);
        cellStyleModuleBeforeLastCell.setBorderBottom(BorderStyle.THIN);
        cellStyleModuleBeforeLastCell.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleBeforeLastCell.setVerticalAlignment(VerticalAlignment.CENTER);
           
        //Création du style pour la dernière cellule pour les données de la feuille "Chiffrage Réalisation" 
        //La dernière cellule contient la charge révisée, cette case est hachuré lorsque sa valeur est égale à 0
           
        //Avec charge révisée
        XSSFCellStyle cellStyleModuleAvecRevise = workbook.createCellStyle();      
        cellStyleModuleAvecRevise.setFont(fontModuleCellCharge);
        cellStyleModuleAvecRevise.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        cellStyleModuleAvecRevise.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleModuleAvecRevise.setBorderLeft(BorderStyle.THIN);
        cellStyleModuleAvecRevise.setBorderRight(BorderStyle.MEDIUM);
        cellStyleModuleAvecRevise.setBorderBottom(BorderStyle.THIN);
        cellStyleModuleAvecRevise.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleAvecRevise.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Sans charge révisée 
        XSSFCellStyle cellStyleModuleSansRevise = workbook.createCellStyle();      
        cellStyleModuleSansRevise.setFont(fontModuleCellCharge);
        cellStyleModuleSansRevise.setFillPattern(FillPatternType.THIN_FORWARD_DIAG);
        cellStyleModuleSansRevise.setBorderLeft(BorderStyle.THIN);
        cellStyleModuleSansRevise.setBorderRight(BorderStyle.MEDIUM);
        cellStyleModuleSansRevise.setBorderBottom(BorderStyle.THIN);
        cellStyleModuleSansRevise.setAlignment(HorizontalAlignment.CENTER);
        cellStyleModuleSansRevise.setVerticalAlignment(VerticalAlignment.CENTER);
		
        
     /*************************************************************************/
     /*** Création de la mise en page de la feuille "Chiffrage Réalisation" ***/
     /*************************************************************************/       
        
        Row headerRowModules = sheetModules.createRow(6);
        
        //Creation des colonnes feuille "Chiffrage Réalisation"
        //On commence à 1 pour commencer à inscrire les données dans la colonne B
        // j - 1 permet d'éviter un OutOfBound
        for(int j = 1; j <= colRealisation.length; j++) {
            
        	//Alimentation première cellule
        	if(j == 1) {
        		
        		Cell cell = headerRowModules.createCell(j);
                cell.setCellValue(colRealisation[j-1]);
                cell.setCellStyle(cellStyleModuleFirstCol);
        		
              //Alimentation dernière cellule    
        	}else if( j == colRealisation.length) {
        		
        		Cell cell = headerRowModules.createCell(j);
                cell.setCellValue(colRealisation[j-1]);
                cell.setCellStyle(cellStyleModuleLastCol);
        		
        	}else {
        		
        		Cell cell = headerRowModules.createCell(j);
                cell.setCellValue(colRealisation[j-1]);
                cell.setCellStyle(cellStyleModuleCol);
        		
        	}	
        	
        }

        
        //Creation des cellules feuille "Chiffrage Réalisation"
        // On commence à 7 car on commence à initialiser à partir de la 7eme ligne.
        // On ne crée pas la dernière cellule "Charge révisée" ici comme le style de la cellule dépend des données qu'elle contient, on crée cette cellule lors de l'alimentation des données 
        for(int j = 7; j < listeModules.size()+7; j++) {
            
        	Row rowModulesCells = sheetModules.createRow(j);
        	
        	//Initialise les colonnes
        	// On commence à 1 pour commencer à inscrire les données dans la colonne B
            // k - 1 permet d'éviter un OutOfBound
        	for(int k = 1; k <= colRealisation.length; k++) {
        	
	        	//Alimentation première cellule
	        	if(k == 1) {
	        		
	        		Cell cell = rowModulesCells.createCell(k);
	                cell.setCellStyle(cellStyleModuleFirstCell);
	        		
	              //Alimentation dernière cellule    
	        	}else if( k == (colRealisation.length - 1)){
	        		
	        		Cell cell = rowModulesCells.createCell(k);
	                cell.setCellStyle(cellStyleModuleBeforeLastCell);
	        		
	        	}else {
	        		
	        		Cell cell = rowModulesCells.createCell(k);
	                cell.setCellStyle(cellStyleModuleCell);
	        		
	        	}
        	
        	}
        	
        }
        
        
     /********************************************************/
     /*** Alimentation de la feuille Chiffrage Réalisation ***/
     /********************************************************/       
        
        int rowNum = 7;
        
        for(Module module: listeModules) {
            Row row = sheetModules.getRow(rowNum++);

            row.getCell(1)
            		.setCellValue(module.getName());

            row.getCell(2)
                    .setCellValue(module.getParameter().getMetric().getType());
            
            row.getCell(3)
            		.setCellValue(module.getParameter().getMetric().getTypology());
            
            //Ajout d'espaces pour l'esthétique
            row.getCell(4)
            		.setCellValue(" " + module.getParameter().getMetric().getTask() + " ");
            
            row.getCell(5)
            		.setCellValue(module.getParameter().getMetric().getComplexity());
            
            row.getCell(6)
            		.setCellValue(module.getParameter().getMetric().getInterventionLevel());
            
            row.getCell(7)
            		.setCellValue(module.getParameter().getUnitCharge());
            
            //Si la charge révisée est égale à 0, on écrit rien
            if(module.getRevisedCharge() == 0) {
            	
            	Cell cell = row.createCell(8);
        		cell.setCellValue("");
        		cell.setCellStyle(cellStyleModuleSansRevise);
            	
            }else{
            	
            	Cell cell = row.createCell(8);
        		cell.setCellValue(module.getRevisedCharge());
        		cell.setCellStyle(cellStyleModuleAvecRevise);
            }       	  
        }
        
        
        /*** Ajustement de la taille des colonnes en fonctions des données **/
        
        for(int i = 0; i <= colRealisation.length; i++) {
            sheetModules.autoSizeColumn(i);
        }
        
        
        
	 }
}
