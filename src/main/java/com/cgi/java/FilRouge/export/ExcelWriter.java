package com.cgi.java.FilRouge.export;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cgi.java.FilRouge.model.Module;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.service.QuoteServiceImpl;

public class ExcelWriter {	
    
	public void exportExcel(Quote quote, HttpServletResponse response, QuoteServiceImpl quoteServiceImplRecu) throws IOException, ServletException{
		
		//Nom du devis
		String quoteName = quote.getName();
				
		//Liste de paramètres pour la première feuille
	    List<Parameter> listeParametresRecu =  new ArrayList<Parameter>();
	    
	    //Liste de modules pour la deuxième feuille
	    List<Module> listeModulesRecu = new ArrayList<Module>();

	   //Liste de phases pour la troisième feuille
	    List<Phase> listePhasesRecu = new ArrayList<Phase>();
	    
	    //On récupère la liste des paramètres
	    listeParametresRecu = quote.getAbacus().getParameters();
	    
	    //On récupère la liste des modules
	    listeModulesRecu = quote.getPhaseRtu().getModules();
		
	    //On récupère la liste des phases
	    listePhasesRecu = quote.getRealPhases();
		
		// Create a Workbook
		XSSFWorkbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

	    /* CreationHelper helps us create instances of various things like DataFormat, 
	       Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	    CreationHelper createHelper = workbook.getCreationHelper(); 
		 
	    /*** Création des différentes feuilles ***/
	    ExcelFeuilleAbaque feuilleAbaque = new ExcelFeuilleAbaque();
	    ExcelFeuilleRealisation feuilleRealisation = new ExcelFeuilleRealisation();
	    ExcelFeuilleGlobal feuilleGlobal = new ExcelFeuilleGlobal();
	    
	    
	    /*** Appel aux différentes méthodes de création ***/
	    feuilleAbaque.creationFeuilleAbaque(workbook, listeParametresRecu);
	    feuilleRealisation.creationFeuilleRealisation(workbook, listeModulesRecu, quoteName);
	    feuilleGlobal.creationFeuilleGlobal(workbook, listePhasesRecu, quoteServiceImplRecu);
	     
	    
	    // Write the output to a file
        String mimeType = "application/vnd.ms-excel";
        response.setContentType(mimeType);
     
        String nameFile = "FEM " + quoteName + ".xlsx";
        
        response.setHeader("Content-Disposition", "attachment; filename=" + nameFile);
  
        ServletOutputStream out = response.getOutputStream();
        
        workbook.write(out);
        
        
        // Closing the workbook
        workbook.close(); 
        
        return;
	 
	}
	 
}
