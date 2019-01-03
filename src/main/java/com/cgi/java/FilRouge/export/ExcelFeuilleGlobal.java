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
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.service.QuoteServiceImpl;

public class ExcelFeuilleGlobal {

	private String[] colGlobal = {"Phases", "Type de ratio", "Charges sans ratio", "% Charges RTU", "Charges", "% Charges sur global", "Justification"};
	
	//Liste de phases pour la troisieme feuille
    private static List<Phase> listePhases = new ArrayList<Phase>();
    
    //Permet de calculer certaines valeurs lors de l'alimentation des données
    private static QuoteServiceImpl quoteServiceImpl;
    
    //Permet de calculer certaines valeurs lors de l'alimentation des données
    private static Quote quote;
	
	public void creationFeuilleGlobal(XSSFWorkbook workbook, List<Phase> listePhasesRecu, QuoteServiceImpl quoteServiceImplRecu) throws IOException{
		 
		listePhases = listePhasesRecu;
		quoteServiceImpl = quoteServiceImplRecu;
		
		//Création feuille Chiffrage Global  
		XSSFSheet sheetPhases = workbook.createSheet("Chiffrage Global");
		
		//Désactive le quadrillage 
		sheetPhases.setDisplayGridlines(false); 
		
	/***********************************************************************************/
	/***         Création police et style de la feuille Chiffrage Global             ***/
	/***                                                                             ***/
	/***        /!\ La page Chiffrage Globale est plus complexe que les autres       ***/
	/***********************************************************************************/  
	
		/*************************************************************************************/
        /*** Création police et style du tableau de données de la feuille Chiffrage Global ***/
        /*************************************************************************************/ 
        
        /*** Création police et style pour le nom des colonnes ***/
        
        //Création d'une police pour le nom des colonnes de la feuille "Chiffrage Global"
        Font fontPhaseTitle = workbook.createFont();
        fontPhaseTitle.setFontHeightInPoints((short) 11);
        fontPhaseTitle.setBold(true);
        fontPhaseTitle.setItalic(true);
        fontPhaseTitle.setColor(IndexedColors.BLACK.getIndex());   
        
        //Création du style pour la première cellule des colonnes de la feuille "Chiffrage Global" 
        XSSFCellStyle cellStylePhaseFirstCol = workbook.createCellStyle();      
        cellStylePhaseFirstCol.setFont(fontPhaseTitle);
        cellStylePhaseFirstCol.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStylePhaseFirstCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseFirstCol.setBorderTop(BorderStyle.MEDIUM);
        cellStylePhaseFirstCol.setBorderLeft(BorderStyle.MEDIUM);
        cellStylePhaseFirstCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseFirstCol.setBorderBottom(BorderStyle.MEDIUM);
        cellStylePhaseFirstCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseFirstCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Création du style pour les cellules des colonnes de la feuille "Chiffrage Global" 
        XSSFCellStyle cellStylePhaseCol = workbook.createCellStyle();      
        cellStylePhaseCol.setFont(fontPhaseTitle);
        cellStylePhaseCol.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStylePhaseCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseCol.setBorderTop(BorderStyle.MEDIUM);
        cellStylePhaseCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseCol.setBorderBottom(BorderStyle.MEDIUM);
        cellStylePhaseCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Création du style pour la première cellule des colonnes de la feuille "Chiffrage Global" 
        XSSFCellStyle cellStylePhaseLastCol = workbook.createCellStyle();      
        cellStylePhaseLastCol.setFont(fontPhaseTitle);
        cellStylePhaseLastCol.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStylePhaseLastCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseLastCol.setBorderTop(BorderStyle.MEDIUM);
        cellStylePhaseLastCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseLastCol.setBorderRight(BorderStyle.MEDIUM);
        cellStylePhaseLastCol.setBorderBottom(BorderStyle.MEDIUM);
        cellStylePhaseLastCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseLastCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        
        /*** Création police et style pour les différentes données ***/
        
        //Création d'une police pour les données de la feuille "Chiffrage Global"
        Font fontPhaseCell = workbook.createFont();
        fontPhaseCell.setFontHeightInPoints((short) 10);
        fontPhaseCell.setColor(IndexedColors.BLACK.getIndex()); 
        
        
        /*** Création du style pour les données ***/
        
        /*** Style "Unité d'oeuvre spécifique", "Sur Global" et "Sans Ratio sur devis" ***/
        
        //Création du style pour la première cellule pour le type de ratio "Unité d'oeuvre spécifique", "Sur Global" et "Sans Ratio sur devis"
        XSSFCellStyle cellStylePhaseUniteFirstCol = workbook.createCellStyle();      
        cellStylePhaseUniteFirstCol.setFont(fontPhaseCell);
        cellStylePhaseUniteFirstCol.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStylePhaseUniteFirstCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseUniteFirstCol.setBorderLeft(BorderStyle.MEDIUM);
        cellStylePhaseUniteFirstCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseUniteFirstCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseUniteFirstCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseUniteFirstCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Création du style pour les cellules pour le type de ratio "Unité d'oeuvre spécifique", "Sur Global" et "Sans Ratio sur devis"
        XSSFCellStyle cellStylePhaseUniteCol = workbook.createCellStyle();      
        cellStylePhaseUniteCol.setFont(fontPhaseCell);
        cellStylePhaseUniteCol.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStylePhaseUniteCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseUniteCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseUniteCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseUniteCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseUniteCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseUniteCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        
        /*** Style "Sur RTU" et "RTU" ***/
        
      //Création du style pour la première cellule pour le type de ratio "Sur RTU" et "RTU"
        XSSFCellStyle cellStylePhaseRtuFirstCol = workbook.createCellStyle();      
        cellStylePhaseRtuFirstCol.setFont(fontPhaseCell);
        cellStylePhaseRtuFirstCol.setBorderLeft(BorderStyle.MEDIUM);
        cellStylePhaseRtuFirstCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseRtuFirstCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseRtuFirstCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseRtuFirstCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Création du style pour les cellules pour le type de ratio "Sur RTU" et "RTU"
        XSSFCellStyle cellStylePhaseRtuCol = workbook.createCellStyle();      
        cellStylePhaseRtuCol.setFont(fontPhaseCell);
        cellStylePhaseRtuCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseRtuCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseRtuCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseRtuCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseRtuCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        
        /*** Style pour colonne "Charge Sans Ratio" ***/
        
        //Création style pour colonne Charge Sans Ratio
        
        //Avec "Sans Ratio"
        XSSFCellStyle cellStylePhaseAvecSansRatioCol = workbook.createCellStyle();      
        cellStylePhaseAvecSansRatioCol.setFont(fontPhaseCell);
        cellStylePhaseAvecSansRatioCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseAvecSansRatioCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseAvecSansRatioCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseAvecSansRatioCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseAvecSansRatioCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Sans "Sans Ratio"
        XSSFCellStyle cellStylePhaseSansSansRatioCol = workbook.createCellStyle();      
        cellStylePhaseSansSansRatioCol.setFont(fontPhaseCell);
        cellStylePhaseSansSansRatioCol.setFillPattern(FillPatternType.THIN_FORWARD_DIAG);
        cellStylePhaseSansSansRatioCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseSansSansRatioCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseSansSansRatioCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseSansSansRatioCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseSansSansRatioCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        
        /*** Style pour colonne "% Charge Global" ***/
        
        //Création style pour colonne Charge Sans Ratio
        
        //Sans Global
        XSSFCellStyle cellStylePhaseSansGlobalCol = workbook.createCellStyle();      
        cellStylePhaseSansGlobalCol.setFont(fontPhaseCell);
        cellStylePhaseSansGlobalCol.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStylePhaseSansGlobalCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseSansGlobalCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseSansGlobalCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseSansGlobalCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseSansGlobalCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseSansGlobalCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Avec Global
        XSSFCellStyle cellStylePhaseAvecGlobalCol = workbook.createCellStyle();      
        cellStylePhaseAvecGlobalCol.setFont(fontPhaseCell);
        cellStylePhaseAvecGlobalCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseAvecGlobalCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseAvecGlobalCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseAvecGlobalCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseAvecGlobalCol.setVerticalAlignment(VerticalAlignment.CENTER);
        

        /*** Style pour colonne "Justification" ***/
        
        //La colonne "Justification" est la colonne qui termine chaque ligne
        //Création style pour colonne Justification
        
        //Avec "Justification"
        XSSFCellStyle cellStylePhaseAvecJustificationCol = workbook.createCellStyle();      
        cellStylePhaseAvecJustificationCol.setFont(fontPhaseCell);
        cellStylePhaseAvecJustificationCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseAvecJustificationCol.setBorderRight(BorderStyle.MEDIUM);
        cellStylePhaseAvecJustificationCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseAvecJustificationCol.setAlignment(HorizontalAlignment.LEFT);
        cellStylePhaseAvecJustificationCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Sans "Justification"
        XSSFCellStyle cellStylePhaseSansJustificationCol = workbook.createCellStyle();      
        cellStylePhaseSansJustificationCol.setFont(fontPhaseCell);
        cellStylePhaseSansJustificationCol.setFillPattern(FillPatternType.THIN_FORWARD_DIAG);
        cellStylePhaseSansJustificationCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseSansJustificationCol.setBorderRight(BorderStyle.MEDIUM);
        cellStylePhaseSansJustificationCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseSansJustificationCol.setAlignment(HorizontalAlignment.LEFT);
        cellStylePhaseSansJustificationCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        
        /*** Création police spéciale pour RTU ***/
        
        //Création d'une police spéciale pour les charges RTU
        Font fontPhaseRtu = workbook.createFont();
        fontPhaseRtu.setFontHeightInPoints((short) 10);
        fontPhaseRtu.setBold(true);
        fontPhaseRtu.setColor(IndexedColors.BLACK.getIndex());
        
        /*** Création style spécial pour RTU ***/
        
        //Création d'un style spécial pour les charges RTU
        XSSFCellStyle cellStylePhaseChargeRtuCol = workbook.createCellStyle();      
        cellStylePhaseChargeRtuCol.setFont(fontPhaseRtu);
        cellStylePhaseChargeRtuCol.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        cellStylePhaseChargeRtuCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseChargeRtuCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseChargeRtuCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseChargeRtuCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseChargeRtuCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseChargeRtuCol.setVerticalAlignment(VerticalAlignment.CENTER);   
        
        /*** Style pour la fin du tableau ***/
        
        //Création du style pour terminer le tableau
        XSSFCellStyle cellStylePhaseFinal = workbook.createCellStyle();      
        cellStylePhaseFinal.setBorderTop(BorderStyle.MEDIUM);
        
     
        /*********************************************************************************************/
        /*** Création police et style du tableau récapitulatif de la feuille Chiffrage Réalisation ***/
        /*********************************************************************************************/ 
        
        //Création d'une police pour le nom des totaux du tableau récapitulatif
        Font fontPhaseRecapFirst = workbook.createFont();
        fontPhaseRecapFirst.setFontHeightInPoints((short) 10);
        fontPhaseRecapFirst.setBold(true);
        fontPhaseRecapFirst.setItalic(true);
        fontPhaseRecapFirst.setColor(IndexedColors.BLACK.getIndex());  
        
        //Création d'une police pour les nombres du tableau récapitulatif
        Font fontPhaseRecapNumber = workbook.createFont();
        fontPhaseRecapNumber.setFontHeightInPoints((short) 10);
        fontPhaseRecapNumber.setBold(true);
        fontPhaseRecapNumber.setColor(IndexedColors.BLACK.getIndex()); 
        
        /*** Style pour le début du tableau récapitulatif ***/
        
        //Création du style pour commencer le tableau
        XSSFCellStyle cellStylePhaseStart = workbook.createCellStyle();      
        cellStylePhaseStart.setBorderBottom(BorderStyle.MEDIUM);
        
        
        /*** Style pour le tableau récapitulatif ***/
        
        //Création du style pour la première cellule du tableau récapitulatif 
        XSSFCellStyle cellStylePhaseRecapFirstCol = workbook.createCellStyle();      
        cellStylePhaseRecapFirstCol.setFont(fontPhaseRecapFirst);
        cellStylePhaseRecapFirstCol.setBorderLeft(BorderStyle.MEDIUM);
        cellStylePhaseRecapFirstCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseRecapFirstCol.setAlignment(HorizontalAlignment.RIGHT);
        cellStylePhaseRecapFirstCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Création du style pour les cellules merged du tableau récapitulatif
        XSSFCellStyle cellStylePhaseRecapCenterCol = workbook.createCellStyle();      
        cellStylePhaseRecapCenterCol.setFont(fontPhaseRecapFirst);
        cellStylePhaseRecapCenterCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseRecapCenterCol.setAlignment(HorizontalAlignment.RIGHT);
        cellStylePhaseRecapCenterCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Création du style pour les cellules des colonnes du tableau récapitulatif
        XSSFCellStyle cellStylePhaseRecapCol = workbook.createCellStyle();      
        cellStylePhaseRecapCol.setFont(fontPhaseRecapNumber);
        cellStylePhaseRecapCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseRecapCol.setBorderRight(BorderStyle.THIN);
        cellStylePhaseRecapCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseRecapCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseRecapCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Création du style pour la dernière cellule des colonnes du tableau récapitulatif
        XSSFCellStyle cellStylePhaseRecapLastCol = workbook.createCellStyle();      
        cellStylePhaseRecapLastCol.setFont(fontPhaseRecapNumber);
        cellStylePhaseRecapLastCol.setBorderLeft(BorderStyle.THIN);
        cellStylePhaseRecapLastCol.setBorderRight(BorderStyle.MEDIUM);
        cellStylePhaseRecapLastCol.setBorderBottom(BorderStyle.THIN);
        cellStylePhaseRecapLastCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseRecapLastCol.setVerticalAlignment(VerticalAlignment.CENTER);

        
        /*****************************************************************************************/
        /*** Création police et style du tableau Charge Totale de la feuille Chiffrage Global  ***/
        /*****************************************************************************************/ 
        
        //Création d'une police pour le nom des totaux du tableau Charge Totale
        Font fontPhaseTotalFirst = workbook.createFont();
        fontPhaseTotalFirst.setFontHeightInPoints((short) 12);
        fontPhaseTotalFirst.setBold(true);
        fontPhaseTotalFirst.setItalic(true);
        fontPhaseTotalFirst.setColor(IndexedColors.BLACK.getIndex());  
        
        //Création d'une police pour les nombres du tableau Charge Totale
        Font fontPhaseTotalNumber = workbook.createFont();
        fontPhaseTotalNumber.setFontHeightInPoints((short) 14);
        fontPhaseTotalNumber.setBold(true);
        fontPhaseTotalNumber.setColor(IndexedColors.BLACK.getIndex());
        
        /*** Style pour le tableau Charge Totale ***/
        
        //Création du style pour la première cellule du tableau Charge Totale
        XSSFCellStyle cellStylePhaseTotalFirstCol = workbook.createCellStyle();      
        cellStylePhaseTotalFirstCol.setFont(fontPhaseTotalFirst);
        cellStylePhaseTotalFirstCol.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        cellStylePhaseTotalFirstCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseTotalFirstCol.setBorderTop(BorderStyle.MEDIUM);
        cellStylePhaseTotalFirstCol.setBorderLeft(BorderStyle.MEDIUM);
        cellStylePhaseTotalFirstCol.setBorderBottom(BorderStyle.MEDIUM);
        cellStylePhaseTotalFirstCol.setAlignment(HorizontalAlignment.RIGHT);
        cellStylePhaseTotalFirstCol.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //Création du style pour les cellules merged du tableau Charge Totale
        XSSFCellStyle cellStylePhaseTotalCenterCol = workbook.createCellStyle();      
        cellStylePhaseTotalCenterCol.setFont(fontPhaseTotalFirst);
        cellStylePhaseTotalCenterCol.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        cellStylePhaseTotalCenterCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseTotalCenterCol.setBorderTop(BorderStyle.MEDIUM);
        cellStylePhaseTotalCenterCol.setBorderBottom(BorderStyle.MEDIUM);
        cellStylePhaseTotalCenterCol.setAlignment(HorizontalAlignment.RIGHT);
        cellStylePhaseTotalCenterCol.setVerticalAlignment(VerticalAlignment.CENTER);   
        
        //Création du style pour la dernière cellule des colonnes du tableau Charge Totale
        XSSFCellStyle cellStylePhaseTotalLastCol = workbook.createCellStyle();      
        cellStylePhaseTotalLastCol.setFont(fontPhaseTotalNumber);
        cellStylePhaseTotalLastCol.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        cellStylePhaseTotalLastCol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStylePhaseTotalLastCol.setBorderTop(BorderStyle.MEDIUM);
        cellStylePhaseTotalLastCol.setBorderLeft(BorderStyle.MEDIUM);
        cellStylePhaseTotalLastCol.setBorderRight(BorderStyle.MEDIUM);
        cellStylePhaseTotalLastCol.setBorderBottom(BorderStyle.MEDIUM);
        cellStylePhaseTotalLastCol.setAlignment(HorizontalAlignment.CENTER);
        cellStylePhaseTotalLastCol.setVerticalAlignment(VerticalAlignment.CENTER);


     /********************************************************************/
     /*** Création de la mise en page de la feuille "Chiffrage Global" ***/
     /********************************************************************/        
        
        Row headerRowPhases = sheetPhases.createRow(1);
        
        //Creation des colonnes feuille "Chiffrage Réalisation"
        //On commence à 1 pour commencer à inscrire les données dans la colonne B
        // l - 1 permet d'éviter un OutOfBound
        for(int l = 1; l <= colGlobal.length; l++) {
            
        	//Alimentation première cellule
        	if(l == 1) {
        		
        		Cell cell = headerRowPhases.createCell(l);
                cell.setCellValue(colGlobal[l-1]);
                cell.setCellStyle(cellStylePhaseFirstCol);
        		
              //Alimentation dernière cellule    
        	}else if( l == colGlobal.length) {
        		
        		Cell cell = headerRowPhases.createCell(l);
                cell.setCellValue(colGlobal[l-1]);
                cell.setCellStyle(cellStylePhaseLastCol);
        		
        	}else {
        		
        		Cell cell = headerRowPhases.createCell(l);
                cell.setCellValue(colGlobal[l-1]);
                cell.setCellStyle(cellStylePhaseCol);
        		
        	}	
        	
        }
        
        
        /***  /!\ Les différents tableaux de la feuille "Chiffrage Global" sont créés lors de l'alimentation des données   ***/

        /*** Alimentation des données ***/
        
        //Les données commencent à la ligne 2
        int rowNum = 2;
        
        //Total Rtu necéssaire pour calculer les valeurs OnRtu
        double totalRtu = 0;

        for(Phase phase: listePhases) {
            Row row = sheetPhases.createRow(rowNum++);
            
            //Dans le cas où le type de ratio est RTU
            if(phase.getPhaseType()== EnumPhaseType.RTU) {
            	
            	totalRtu = phase.getValue();
            	quote = phase.getQuote();

            	/*** On crée chaque cellule RTU une à une ***/
            	
            	Cell cell1 = row.createCell(1);
            	cell1.setCellStyle(cellStylePhaseRtuFirstCol);
                cell1.setCellValue(phase.getName());
            	
                Cell cell2 = row.createCell(2);
                cell2.setCellStyle(cellStylePhaseRtuCol);
            	cell2.setCellValue(EnumPhaseType.RTU.getValue());
            	
            	//la colonne "Charges sans ratio" est vide pour le type de ratio RTU	
            	Cell cell3 = row.createCell(3);
                cell3.setCellStyle(cellStylePhaseSansSansRatioCol);
            	cell3.setCellValue("");

            	
            	//la colonne "% Charges RTU" est toujours à 100% pour le type de ratio RTU	
            	Cell cell4 = row.createCell(4);
                cell4.setCellStyle(cellStylePhaseChargeRtuCol);
            	cell4.setCellValue("100%");

            	Cell cell5 = row.createCell(5);
                cell5.setCellStyle(cellStylePhaseChargeRtuCol);
            	cell5.setCellValue(phase.getValue());

            	
            	//la colonne "% Charges sur global" est vide pour le type de ratio RTU
            	Cell cell6 = row.createCell(6);
                cell6.setCellStyle(cellStylePhaseSansGlobalCol);
            	cell6.setCellValue("");
            	
            	//la colonne "Justification" est vide pour le type de ratio RTU
            	Cell cell7 = row.createCell(7);
                cell7.setCellStyle(cellStylePhaseSansJustificationCol);
            	cell7.setCellValue("");
            	
            }
            
          //Dans le cas où le type de ratio est ONRTU
            if(phase.getPhaseType()== EnumPhaseType.ONRTU) {
            	
            	
            	/*** On crée chaque cellule ONRTU une à une ***/
            	
            	Cell cell1 = row.createCell(1);
            	cell1.setCellStyle(cellStylePhaseRtuFirstCol);
                cell1.setCellValue(phase.getName());
            	
                Cell cell2 = row.createCell(2);
                cell2.setCellStyle(cellStylePhaseRtuCol);
            	cell2.setCellValue(EnumPhaseType.ONRTU.getValue());
            	
            	//la colonne "Charges sans ratio" est vide pour le type de ratio ONRTU	
            	Cell cell3 = row.createCell(3);
                cell3.setCellStyle(cellStylePhaseSansSansRatioCol);
            	cell3.setCellValue("");
            	
            	//la colonne "% Charges RTU" est toujours à 100% pour le type de ratio RTU	
            	Cell cell4 = row.createCell(4);
                cell4.setCellStyle(cellStylePhaseRtuCol);
            	cell4.setCellValue(phase.getValue() + "%");

            	
            	Cell cell5 = row.createCell(5);
                cell5.setCellStyle(cellStylePhaseRtuCol);
            	cell5.setCellValue(quoteServiceImpl.calculChargePourcentageRtu(phase.getValue(),totalRtu));

            	
            	//la colonne "% Charges sur global" est vide pour le type de ratio RTU
            	Cell cell6 = row.createCell(6);
                cell6.setCellStyle(cellStylePhaseSansGlobalCol);
            	cell6.setCellValue("");
            	
            	//la colonne "Justification" est vide pour le type de ratio RTU
            	Cell cell7 = row.createCell(7);
                cell7.setCellStyle(cellStylePhaseSansJustificationCol);
            	cell7.setCellValue("");
            	   	
            }
            
            //Dans le cas où le type de ratio est WITHOUTRATIO
            if(phase.getPhaseType()== EnumPhaseType.WITHOUTRATIO) {
            	
            	/*** On crée chaque cellule WITHOUTRATIO une à une ***/
            	
            	Cell cell1 = row.createCell(1);
            	cell1.setCellStyle(cellStylePhaseUniteFirstCol);
                cell1.setCellValue(phase.getName());
            	
                Cell cell2 = row.createCell(2);
                cell2.setCellStyle(cellStylePhaseUniteCol);
            	cell2.setCellValue(EnumPhaseType.WITHOUTRATIO.getValue());
            	
            	Cell cell3 = row.createCell(3);
                cell3.setCellStyle(cellStylePhaseAvecSansRatioCol);
            	cell3.setCellValue(phase.getValue());
            	
            	//la colonne "% Charges RTU" est vide pour le type de ratio WITHOUTRATIO
            	Cell cell4 = row.createCell(4);
                cell4.setCellStyle(cellStylePhaseUniteCol);
            	cell4.setCellValue("");

            	//la colonne "Charges" est vide pour le type de ratio WITHOUTRATIO
            	Cell cell5 = row.createCell(5);
                cell5.setCellStyle(cellStylePhaseUniteCol);
            	cell5.setCellValue("");

            	
            	//la colonne "% Charges sur global" est vide pour le type de ratio WITHOUTRATIO
            	Cell cell6 = row.createCell(6);
                cell6.setCellStyle(cellStylePhaseSansGlobalCol);
            	cell6.setCellValue("");
            	
            	//le type de ratio WITHOUTRATIO possède une justification
            	Cell cell7 = row.createCell(7);
                cell7.setCellStyle(cellStylePhaseAvecJustificationCol);
            	cell7.setCellValue(phase.getJustification());
            	
            }
            
          //Dans le cas où le type de ratio est UOS
            if(phase.getPhaseType()== EnumPhaseType.UOS) {
            	
            	/*** On crée chaque cellule UOS une à une ***/
            	
            	Cell cell1 = row.createCell(1);
            	cell1.setCellStyle(cellStylePhaseUniteFirstCol);
                cell1.setCellValue(phase.getName());
            	
                Cell cell2 = row.createCell(2);
                cell2.setCellStyle(cellStylePhaseUniteCol);
            	cell2.setCellValue(EnumPhaseType.UOS.getValue());
            	
            	Cell cell3 = row.createCell(3);
                cell3.setCellStyle(cellStylePhaseAvecSansRatioCol);
            	cell3.setCellValue(phase.getValue());
            	
            	//la colonne "% Charges RTU" est vide pour le type de ratio UOS
            	Cell cell4 = row.createCell(4);
                cell4.setCellStyle(cellStylePhaseUniteCol);
            	cell4.setCellValue("");

            	//la colonne "Charges" est vide pour le type de ratio UOS
            	Cell cell5 = row.createCell(5);
                cell5.setCellStyle(cellStylePhaseUniteCol);
            	cell5.setCellValue("");

            	
            	//la colonne "% Charges sur global" est vide pour le type de ratio UOS
            	Cell cell6 = row.createCell(6);
                cell6.setCellStyle(cellStylePhaseSansGlobalCol);
            	cell6.setCellValue("");
            	
            	//le type de ratio UOS possède une justification
            	Cell cell7 = row.createCell(7);
                cell7.setCellStyle(cellStylePhaseAvecJustificationCol);
            	cell7.setCellValue(phase.getJustification());
            	
            }

          //Dans le cas où le type de ratio est ONGLOBAL
            if(phase.getPhaseType()== EnumPhaseType.ONGLOBAL) {
            	
            	/*** On crée chaque cellule ONGLOBAL une à une ***/
            	
            	Cell cell1 = row.createCell(1);
            	cell1.setCellStyle(cellStylePhaseUniteFirstCol);
                cell1.setCellValue(phase.getName());
            	
                Cell cell2 = row.createCell(2);
                cell2.setCellStyle(cellStylePhaseUniteCol);
            	cell2.setCellValue(EnumPhaseType.ONGLOBAL.getValue());
            	
            	Cell cell3 = row.createCell(3);
                cell3.setCellStyle(cellStylePhaseSansSansRatioCol);
            	cell3.setCellValue("");
            	
            	//la colonne "% Charges RTU" est vide pour le type de ratio ONGLOBAL
            	Cell cell4 = row.createCell(4);
                cell4.setCellStyle(cellStylePhaseUniteCol);
            	cell4.setCellValue("");

            	//la colonne "Charges" est vide pour le type de ratio ONGLOBAL
            	Cell cell5 = row.createCell(5);
                cell5.setCellStyle(cellStylePhaseUniteCol);
            	cell5.setCellValue("");

            	Cell cell6 = row.createCell(6);
                cell6.setCellStyle(cellStylePhaseAvecGlobalCol);
            	cell6.setCellValue(phase.getValue() + "%");
            	
            	//le type de ratio ONGLOBAL ne possède pas de justification
            	Cell cell7 = row.createCell(7);
                cell7.setCellStyle(cellStylePhaseSansJustificationCol);
            	cell7.setCellValue("");
            	
            	
            }


        }
        

        /*** Bordure de fin du tableau ***/
        
        //Ligne en dessous de la fin du tableau
        Row rowEndData = sheetPhases.createRow(rowNum++);
        
        for(int l = 1; l <= colGlobal.length; l++) {
            
        	Cell cell = rowEndData.createCell(l);
            cell.setCellStyle(cellStylePhaseFinal); 	
        }
        
        
        /*** Ajustement de la taille des colonnes en fonctions des données **/
        /*** à faire avant le tableau global et le tableau de la charge totale ***/
        
        for(int i = 0; i <= colGlobal.length; i++) {
            sheetPhases.autoSizeColumn(i);
        }
        
        
/*** Tableau récapitulatif Global ***/
        
        //Permet de faire la première ligne de bordure pour le tableau
        Row rowStartRecap = sheetPhases.createRow(rowNum++);
        
        // /!\ On enleve le signe <= et on laisse < pour que le tableau récap ne s'aligne pas avec Jusitification et que les autres données soient alignées
        for(int l = 1; l < colGlobal.length; l++) {
            
        	Cell cell = rowStartRecap.createCell(l);
            cell.setCellStyle(cellStylePhaseStart); 	
        }
        
        
        //Première ligne du tableau récapitulatif global
        Row rowFirstRecap = sheetPhases.createRow(rowNum++);
        
        // /!\ On enleve le signe <= et on laisse < pour que le tableau récap ne s'aligne pas avec Jusitification et que les autres données soient alignées
        for(int l = 1; l < colGlobal.length; l++) {
            
        	if(l == 1) {
        		
        		Cell cell = rowFirstRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapFirstCol);
        	
            //Permet d'aligner les colonnes avec les datas
        	}else if (l == colGlobal.length - 2) {
        		
        		Cell cell = rowFirstRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapCol);
                cell.setCellValue(quote.getRtuAndOnRtuSubTotal());
        		
            //Permet d'aligner les colonnes avec les datas	
        	}else if (l == colGlobal.length - 1) {
        		
        		Cell cell = rowFirstRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapLastCol);
        		
        	}else {
        		
        		Cell cell = rowFirstRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapCenterCol);
                
              //Alimentation du libellé
                if(l == colGlobal.length - 3) {
                	
                	cell.setCellValue("Charge Totale des phases avec Ratio sur RTU + RTU");
                	
                }
        	}
        	 	
        }
        
      //Deuxième ligne du tableau récapitulatif global
        Row rowSecondRecap = sheetPhases.createRow(rowNum++);
        
        // /!\ On enleve le signe <= et on laisse < pour que le tableau récap ne s'aligne pas avec Jusitification et que les autres données soient alignées
        for(int l = 1; l < colGlobal.length; l++) {
            
        	if(l == 1) {
        		
        		Cell cell = rowSecondRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapFirstCol);
        	
            //Permet d'aligner les colonnes avec les datas
        	}else if (l == colGlobal.length - 2) {
        		
        		Cell cell = rowSecondRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapCol);
                cell.setCellValue(quoteServiceImpl.calculSansRatio(quote));
        		
            //Permet d'aligner les colonnes avec les datas	
        	}else if (l == colGlobal.length - 1) {
        		
        		Cell cell = rowSecondRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapLastCol);
        		
        	}else {
        		
        		Cell cell = rowSecondRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapCenterCol);
                
              //Alimentation du libellé
                if(l == colGlobal.length - 3) {
                	
                	cell.setCellValue("Charge Totale des phases Sans Ratio");
                	
                }
        	}
        	 	
        }
        
        
        //Troisième ligne du tableau récapitulatif global
        Row rowThirdRecap = sheetPhases.createRow(rowNum++);
        
        // /!\ On enleve le signe <= et on laisse < pour que le tableau récap ne s'aligne pas avec Jusitification et que les autres données soient alignées
        for(int l = 1; l < colGlobal.length; l++) {
            
        	if(l == 1) {
        		
        		Cell cell = rowThirdRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapFirstCol);
        	
            //Permet d'aligner les colonnes avec les datas
        	}else if (l == colGlobal.length - 2) {
        		
        		Cell cell = rowThirdRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapCol);
                cell.setCellValue(quoteServiceImpl.calculArrondiChargeTotale(quote.getOnGlobalSubTotal()));
        		
            //Permet d'aligner les colonnes avec les datas	
        	}else if (l == colGlobal.length - 1) {
        		
        		Cell cell = rowThirdRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapLastCol);
                cell.setCellValue(quoteServiceImpl.calculSommePourcentageGlobal(listePhases) + "%");
        		
        	}else {
        		
        		Cell cell = rowThirdRecap.createCell(l);
                cell.setCellStyle(cellStylePhaseRecapCenterCol);
                
              //Alimentation du libellé
                if(l == colGlobal.length - 3) {
                	
                	cell.setCellValue("Charge totale et ratio total des phases avec ratio sur global");
                	
                }
  
        	}
        	 	
        }
        
        /*** Bordure de fin du tableau récapitulatif ***/
        
        //Ligne en dessous de la fin du tableau
        Row rowEndRecap = sheetPhases.createRow(rowNum++);
        
        for(int l = 1; l < colGlobal.length; l++) {
            
        	Cell cell = rowEndRecap.createCell(l);
            cell.setCellStyle(cellStylePhaseFinal); 	
        }
        
        
        /*** Création du tableau contenant la charge totale ***/
        
        /*** Il y a 3 cellules à créer ***/
        
        //Permet de faire la première ligne de bordure pour le tableau
        Row rowTotal = sheetPhases.createRow(rowNum++);
        
        Cell cellFirstTotal = rowTotal.createCell(4);
        cellFirstTotal.setCellStyle(cellStylePhaseTotalFirstCol);
        
        Cell cellSecondTotal = rowTotal.createCell(5);
        cellSecondTotal.setCellStyle(cellStylePhaseTotalCenterCol);
        cellSecondTotal.setCellValue("Charge Totale (j.h)");
        
        Cell cellThirdTotal = rowTotal.createCell(6);
        cellThirdTotal.setCellStyle(cellStylePhaseTotalLastCol);
        cellThirdTotal.setCellValue(quote.getTotal());
        
        
        
        
	 }
}
