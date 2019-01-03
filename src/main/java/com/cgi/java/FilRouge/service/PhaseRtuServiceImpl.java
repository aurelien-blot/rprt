package com.cgi.java.FilRouge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.model.Module;
import com.cgi.java.FilRouge.model.PhaseRtu;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.repository.PhaseRtuRepo;
import com.cgi.java.FilRouge.service.base.BaseService;

@Service
public class PhaseRtuServiceImpl extends BaseService<PhaseRtuRepo, PhaseRtu> {

	//Calcul pour le total RTU (Total de l'étape 2)
	public double calculTotalRtu(PhaseRtu phaseRtu) {
		
		double totalRtu = 0;
			
		//Calcul pour le total RTU (Total de l'étape 2)
		for (Module module : phaseRtu.getModules()) {
			
			//revised_charge en base ne devrait pas pouvoir être à null
			if(module.getRevisedCharge() == 0) {
			
				totalRtu += module.getParameter().getUnitCharge();
				
			}else{
				
				totalRtu += module.getRevisedCharge();
			}
		}
		
		return totalRtu;
	}	
	
}
