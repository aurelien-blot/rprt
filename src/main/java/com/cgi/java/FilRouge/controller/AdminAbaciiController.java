package com.cgi.java.FilRouge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Metric;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.Role;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.AbacusServiceImpl;
import com.cgi.java.FilRouge.service.ContractServiceImpl;
import com.cgi.java.FilRouge.service.MetricServiceImpl;

import net.minidev.json.JSONArray;

@Controller
@RequestMapping("/administration/abacii")

public class AdminAbaciiController {
	
	@Autowired
	AbacusServiceImpl abacusServiceImpl;
	
	@Autowired
	ContractServiceImpl contractServiceImpl;
	
	@Autowired
	MetricServiceImpl metricServiceImpl;
	
	@GetMapping(value={"", "/"})
	public String adminAbaciiList(Model model) {

		List<Abacus> abacii = abacusServiceImpl.findAll();
		
		model.addAttribute("abacii", abacii);

	    return "/admin/abaciiAdmin";
	}
	
	@GetMapping(value={"/archive/{abacusId}"})
	public ModelAndView deleteUser(Model model, @PathVariable("abacusId") int abacusId) {
		
		Abacus abacus = abacusServiceImpl.findById(abacusId);
		abacusServiceImpl.archiveAbacus(abacus);
		
		return new ModelAndView("redirect:/administration/abacii/");
	}
	
	@GetMapping(value={"/create", "/create/"})
	public String createAbacus(Model model) {
		
		Abacus abacus= new Abacus();
		List<Contract> contracts = contractServiceImpl.findAll();
		List<Abacus> abacii = abacusServiceImpl.findAll();
		List<String> allTypes = metricServiceImpl.findDistinctType(); 
		List<String> allRoles = metricServiceImpl.findDistinctRole(); 
		List<String> allTypologies = metricServiceImpl.findDistinctTypology(); 
		List<String> allComplexities = metricServiceImpl.findDistinctComplexity(); 
		List<String> allInterventionLvls = metricServiceImpl.findDistinctInterventionLvl(); 
		
		model.addAttribute("allTypes", allTypes);
		model.addAttribute("allRoles", allRoles);
		model.addAttribute("allTypologies", allTypologies);
		model.addAttribute("allComplexities", allComplexities);
		model.addAttribute("allInterventionLvls", allInterventionLvls);
		model.addAttribute("abacus", abacus);
		model.addAttribute("abacii", abacii);
		model.addAttribute("allContracts",contracts);
		
	    return "/admin/abacusAdmin";
	}
	
	@GetMapping(value={"/create/{abacusId}"})
	public String modifAbacus(@PathVariable("abacusId") int abacusId, Model model) {
		
		Abacus abacus= abacusServiceImpl.findById(abacusId);
		if(!abacus.isFinished()) {
			List<Contract> contracts = contractServiceImpl.findAll();
			List<Abacus> abacii = abacusServiceImpl.findAll();
			List<String> allTypes = metricServiceImpl.findDistinctType(); 
			List<String> allRoles = metricServiceImpl.findDistinctRole(); 
			List<String> allTypologies = metricServiceImpl.findDistinctTypology(); 
			List<String> allComplexities = metricServiceImpl.findDistinctComplexity(); 
			List<String> allInterventionLvls = metricServiceImpl.findDistinctInterventionLvl(); 
						
			model.addAttribute("allTypes", allTypes);
			model.addAttribute("allRoles", allRoles);
			model.addAttribute("allTypologies", allTypologies);
			model.addAttribute("allComplexities", allComplexities);
			model.addAttribute("allInterventionLvls", allInterventionLvls);
			model.addAttribute("abacus", abacus);
			model.addAttribute("abacii", abacii);
			model.addAttribute("readOnly", false);

			model.addAttribute("allContracts",contracts);
			
		    return "/admin/abacusAdmin";
		}
		else {
			return "error";
		}

	}
	
	@GetMapping(value={"/consult/{abacusId}"})
	public String consultAbacus(@PathVariable("abacusId") int abacusId, Model model) {
		
		Abacus abacus= abacusServiceImpl.findById(abacusId);
		if(abacus.isFinished()) {			
			model.addAttribute("abacus", abacus);
			model.addAttribute("readOnly", true);
			
		    //return "/admin/abacusConsultAdmin";
			 return "/admin/abacusAdmin";
		}
		else {
			return "error";
		}

	}
	
	
}
