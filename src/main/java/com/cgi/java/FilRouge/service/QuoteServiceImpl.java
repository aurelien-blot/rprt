package com.cgi.java.FilRouge.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.dto.QuoteFormDto;
import com.cgi.java.FilRouge.dto.mapper.QuoteDtoMapper;
import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.cgi.java.FilRouge.enums.EnumStatus;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Module;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.PhaseRtu;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.repository.QuoteRepo;
import com.cgi.java.FilRouge.service.base.BaseService;

@Service
public class QuoteServiceImpl extends BaseService<QuoteRepo, Quote>{
	
	@Autowired
	QuoteDtoMapper quoteMapper;
	
	@Autowired
	QuoteServiceImpl quoteServiceImpl;
		
	@Autowired
	PhaseServiceImpl phaseServiceImpl;
	
	@Autowired
	PhaseRtuServiceImpl phaseRtuServiceImpl;
	
	@Autowired
	ModuleServiceImpl moduleServiceImpl;
	
	public List<Quote> findBySearchQuery(String query){
		String[] explode = query.split(" ");
		String buffer = "";
		for(String ex : explode) {
			buffer += "%" + ex + "%";
		}
				
		List<Quote> quotes = this.repository.findByNameLikeOrDescriptionLike(buffer, buffer);
		
		return quotes;
	}
	
	public QuoteFormDto findAndMakeAsDto(int quoteId) {
		Quote quoteTarget = findById(quoteId);
		QuoteFormDto quoteForm = quoteMapper.toDTO(quoteTarget);
		
		return quoteForm;
	}
	

	public List<Parameter> findParametersFromIdQuote(int quoteId){
		Quote quote = this.repository.findById(quoteId);
		return quote.getAbacus().getParameters();
	}


	public String generateUniqueCode(Quote q) {
		long sequence = this.repository.countByProject(q.getProject())+1;
		Project project = q.getProject();
		
		String code = project.getShortName();
		code = code.replace(" ", "");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(project.getCreationDate());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		
		String yearString = String.format("%02d", year);
		String monthString = String.format("%02d", month);
		String sequenceString = String.format("%03d", sequence);
		return code + '-' + yearString + monthString + '-' + sequenceString;
	}

	public void initQuote(Quote quote) {
		PhaseRtu phaseRtu = new PhaseRtu();
		
		
		//Module moduleNew = new Module("", uotNew, null, 0);
		phaseRtu.setName("Réalisation et tests unitaires");
		quoteServiceImpl.save(quote);
		phaseRtu.setQuote(quote);
		phaseRtu.setValue(0);		
		quote.setStatus(EnumStatus.INPROGRESS);
		
		phaseServiceImpl.save(phaseRtu);

		quoteServiceImpl.save(quote);
	}
	
	
	// Calcul de toutes les charges.
	public void setTotalCalculsFromQuote(Quote quote) {
	
		//Calcul pour le total RTU (Total de l'étape 2)
		double calculTotalRtu = phaseRtuServiceImpl.calculTotalRtu(quote.getPhaseRtu());
		quote.getPhaseRtu().setValue(calculTotalRtu);
		
		//Calcul avec Ratio Rtu + Rtu
		double calculAvecRatioRtuEtRtu = calculAvecRatioRtuEtRtu(quote);
		quote.setRtuAndOnRtuSubTotal(calculAvecRatioRtuEtRtu);
	
		//Calcul sans Ratio 
		double calculSansRatio = calculSansRatio(quote);
		quote.setWithoutRatioSubTotal(calculSansRatio);
				
		//Calcul charge des phases avec ratio sur global
		double calculRatioSurGlobal = calculRatioSurGlobal(quote, calculAvecRatioRtuEtRtu, calculSansRatio);
		quote.setOnGlobalSubTotal(calculRatioSurGlobal);		
		
		//Calcul charge Totale
		double calculChargeTotale = calculChargeTotale(calculAvecRatioRtuEtRtu, calculSansRatio, calculRatioSurGlobal);
		quote.setTotal(calculChargeTotale);
		
	}	
	
	
	//Calcul pour le total des phases avec ratio RTU + RTU
	public double calculAvecRatioRtuEtRtu(Quote quote) {
		
		double rtu=0;
		double sommePourcentageAvecRatio = 0;
		double avecRatioRtuEtRtu;
		
		for (Phase phase : quote.getRealPhases()) {
			
			//On récupère le type de phase pour les calculs
			if(phase.getPhaseType() == EnumPhaseType.ONRTU) {
			
				sommePourcentageAvecRatio += phase.getValue();
			}
			else if(phase.getPhaseType() == EnumPhaseType.RTU){
				rtu=phase.getValue();
			}
		}
		
		
		avecRatioRtuEtRtu = rtu + (rtu * (sommePourcentageAvecRatio / 100));	
			
		return (avecRatioRtuEtRtu);
	}

	//Calcul pour le total des phases sans ratio
	public double calculSansRatio(Quote quote) {
			
		double sansRatio = 0;
			
		for (Phase phase : quote.getRealPhases()) {
				
			//On récupère le type de phase pour les calculs
			if(phase.getPhaseType() == EnumPhaseType.UOS || phase.getPhaseType() == EnumPhaseType.WITHOUTRATIO ) {
			
				sansRatio += phase.getValue();
			}
		}
		
		return (sansRatio);
	}
	
	//Calcul pour le ratio sur le global
	public double calculRatioSurGlobal(Quote quote, double calculAvecRatioRtuEtRtu, double calculSansRatio) {
					
		double ratioSurGlobal = 0;
		double totalRatioSurGlobal;
			
		for (Phase phase : quote.getRealPhases()) {
					
			//On récupère le type de phase pour les calculs
			if(phase.getPhaseType() == EnumPhaseType.ONGLOBAL ) {
			
				ratioSurGlobal += phase.getValue();
			}
		
		}
		
		totalRatioSurGlobal = (calculAvecRatioRtuEtRtu + calculSansRatio) * (ratioSurGlobal/100);
		
		return (totalRatioSurGlobal);
	}

	//Calcul des charges totales
	public double calculChargeTotale(double calculAvecRatioRtuEtRtu, double calculSansRatio, double ratioSurGlobal) {
							
		double chargeTotale = calculAvecRatioRtuEtRtu + calculSansRatio + ratioSurGlobal;
		double chargeTotaleArrondie = 0;
		
		int cutTot = (int) (chargeTotale * 100);
		double cut = chargeTotale / 100;
		double reste = cutTot % 100;
	
		
		if(reste <= 50 && reste > 0) {
			
			chargeTotaleArrondie = (int) (chargeTotale) + 0.5;
			
		}else if(reste > 50) {
			
			chargeTotaleArrondie = (int) (chargeTotale) + 1;
		}else {
		
			chargeTotaleArrondie = (int) (chargeTotale);
		}
		
		//retourne la charge totale arrondie au demi près
		return(chargeTotaleArrondie);
	}

	
	//Calcul des charges totales
	public double calculChargePourcentageRtu(double pourcentageRtu, double totalRtu) {
								
		double chargeRtu = totalRtu * (pourcentageRtu/100); 
		
		double deuxChiffresAprèsLaVirgule = (((int)(chargeRtu*100))/100.);

		//retourne la charge sur Rtu en fonction du pourcentage
		return(deuxChiffresAprèsLaVirgule);
	}
	
	//Calcul des charges totales
	public double calculSommePourcentageGlobal(List<Phase> listePhases) {
								
		double sommePourcentageGlobal = 0.0;
				
		 for(Phase phase: listePhases) {
			 
			 if(phase.getPhaseType()== EnumPhaseType.ONGLOBAL) {
				 
				 sommePourcentageGlobal += phase.getValue();
			 }
		 }

		//retourne du pourcentage totale sur le global
		return(sommePourcentageGlobal);
	}
	
	//Calcul arrondissant les charges totales.
	public double calculArrondiChargeTotale(double chargeTotale) {
											
					double deuxChiffresAprèsLaVirgule = (((int)(chargeTotale*100))/100.);
								
					return(deuxChiffresAprèsLaVirgule);
				}
}
	

