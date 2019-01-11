package com.cgi.java.FilRouge.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.config.BeanContainer;
import org.dozer.util.DefaultClassLoader;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;
import com.cgi.java.FilRouge.dto.QuoteFormDto;
import com.cgi.java.FilRouge.dto.mapper.QuoteDtoMapper;
import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.cgi.java.FilRouge.export.ExcelWriter;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.enums.EnumStatus;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.QuoteTemplate;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.service.ContractServiceImpl;
import com.cgi.java.FilRouge.service.MetricServiceImpl;
import com.cgi.java.FilRouge.service.ModuleServiceImpl;
import com.cgi.java.FilRouge.service.PhaseServiceImpl;
import com.cgi.java.FilRouge.service.ProjectServiceImpl;
import com.cgi.java.FilRouge.service.QuoteServiceImpl;
import com.cgi.java.FilRouge.service.QuoteTemplateServiceImpl;
import com.cgi.java.FilRouge.service.TeamServiceImpl;
import com.cgi.java.FilRouge.service.dto.QuoteFormServiceImpl;

@Controller
@SessionAttributes(value={"quote", "phase"})
@RequestMapping("/devis")
public class QuoteController {
	
	@Autowired
	QuoteServiceImpl quoteServiceImpl;
	
	@Autowired
	PhaseServiceImpl phaseServiceImpl;
	
	@Autowired
	ModuleServiceImpl moduleServiceImpl;
	
	@Autowired
	QuoteDtoMapper quoteMapper;
	
	@Autowired
	QuoteTemplateServiceImpl quoteTemplateServiceImpl;
	
	@Autowired
	QuoteFormServiceImpl quoteFormServiceImpl;
	@Autowired
	QuoteDtoMapper quoteDtoMapper;

	@Autowired
	MetricServiceImpl metricServiceImpl;
	
	@Autowired
	TeamServiceImpl teamServiceImpl;
	
	@Autowired
	ContractServiceImpl contractServiceImpl;
	
	@Autowired
	ProjectServiceImpl projectServiceImpl;
	
	@GetMapping(value={"recherche", "recherche/"})
    public String search(Model model, @RequestParam(value="q", required=false) String q) {
		List<Quote> allQuotes = quoteServiceImpl.findAll();
		model.addAttribute("quotes", allQuotes);
		
        return "/devis/search";
    }

	@GetMapping(value={"/creation", "/creation/"})
    public String createForm(Model model) {
	
		List<Contract> contracts = contractServiceImpl.findAll();
		List<Project> projects = projectServiceImpl.findAll();
		List<QuoteTemplate> quoteTemplates =  quoteTemplateServiceImpl.findAll();
		List<Team> quoteTeam =  teamServiceImpl.findAll();

		
		model.addAttribute("allContracts",contracts);
		model.addAttribute("allProjects",projects);
		model.addAttribute("allQuoteTemplates",quoteTemplates);
		model.addAttribute("quote", new Quote());
		model.addAttribute("team", quoteTeam);
		return "/devis/create";
    }
	
	
	@PostMapping(value={"/creation/metrics", "/creation/metrics/"})
    public String  saveQuoteInfos(@ModelAttribute Quote quote, BindingResult result,ModelMap modelMap, Model model,SessionStatus status) {
		
		List<Contract> contracts = contractServiceImpl.findAll();
		model.addAttribute("allContracts",contracts);
		model.addAttribute("quoteContract", quote.getProject().getTeams().get(0).getContract().getId());

		return "/devis/metricsChoice";
    }
	
	@GetMapping(value={"/creation/metrics", "/creation/metrics/"})
    public String backOnAbacusChoice(SessionStatus status) {
		
		return "/home";
    }
	

	@PostMapping(value={"/init", "/init/"})
    public ModelAndView  initQuote (@ModelAttribute Quote quote, BindingResult result,ModelMap modelMap, Model model,SessionStatus status) {
		quote.setCode(quoteServiceImpl.generateUniqueCode(quote));
		quoteServiceImpl.initQuote(quote);
		return new ModelAndView("redirect:/devis/modif/"+quote.getId());
    }
	
	
	@GetMapping(value={"/modif/{quoteId}/{idDivView}", "/modif/{quoteId}/", "/modif/{quoteId}"})
    public String modifQuote (@PathVariable("quoteId") int quoteId,@PathVariable("idDivView") Optional<String> idDivView, Model model, SessionStatus status) {
		String idDivViewReal;
		boolean viewOnly =false;
		boolean consult;
		
		if(!idDivView.isPresent()) {
			idDivViewReal ="2";
			consult = true;
		}
		
		else {
			idDivViewReal = idDivView.get();
			consult = false;
		}

		
		model.addAttribute("consult",consult);

		QuoteFormDto quoteForm = quoteServiceImpl.findAndMakeAsDto(quoteId);
		model.addAttribute("idDivView", idDivViewReal);
		model.addAttribute("quote", quoteForm);
		// J AUTORISE LA MODIF SI LE STATUT EST EN COURS ET C EST TOUT
		
		if(quoteForm.getStatus()!=EnumStatus.INPROGRESS) {
	
			return "error/status";
		}
		
		
		model.addAttribute("viewOnly", viewOnly);
		return "devis/modif";
    }
	
	@GetMapping(value={"/view/{quoteId}/{idDivView}", "/view/{quoteId}/", "/view/{quoteId}"})
    public String viewQuote (@PathVariable("quoteId") int quoteId,@PathVariable("idDivView") Optional<String> idDivView, Model model, SessionStatus status) {
		
		String idDivViewReal;
		if(!idDivView.isPresent()) {
			idDivViewReal ="2";
		}
		else {
			idDivViewReal = idDivView.get();
		}
		
		boolean viewOnly = true;
		model.addAttribute("viewOnly", viewOnly);
		
		QuoteFormDto quoteForm = quoteServiceImpl.findAndMakeAsDto(quoteId);
		model.addAttribute("idDivView", idDivViewReal);
		model.addAttribute("quote", quoteForm);

		return "devis/modif";
    }
	

	//Pour Export Excel
	@GetMapping(value={"/excel/{quoteId}/", "/excel/{quoteId}"})
    public ModelAndView quoteToExcel (@PathVariable("quoteId") int quoteId, HttpServletResponse response) throws IOException, ServletException {
		
		Quote quote = quoteServiceImpl.findById(quoteId);

	    //Export Excel	
	    ExcelWriter excel = new ExcelWriter();
	    excel.exportExcel(quote, response, quoteServiceImpl);
	    
	    return null;
    }

	
}