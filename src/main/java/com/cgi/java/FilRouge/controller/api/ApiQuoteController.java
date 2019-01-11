package com.cgi.java.FilRouge.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgi.java.FilRouge.dto.QuoteFormDto;
import com.cgi.java.FilRouge.dto.mapper.QuoteDtoMapper;
import com.cgi.java.FilRouge.enums.EnumStatus;
import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Metric;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.model.serializer.MetricSerializer;
import com.cgi.java.FilRouge.model.serializer.AbacusSerializer;
import com.cgi.java.FilRouge.model.serializer.ParameterSerializer;
import com.cgi.java.FilRouge.model.serializer.ProjectSerializer;
import com.cgi.java.FilRouge.model.serializer.QuoteSerializer;
import com.cgi.java.FilRouge.model.serializer.SearchProjectSerializer;
import com.cgi.java.FilRouge.model.serializer.SearchQuoteSerializer;
import com.cgi.java.FilRouge.model.serializer.TeamSerializer;
import com.cgi.java.FilRouge.service.AbacusServiceImpl;
import com.cgi.java.FilRouge.service.ContractServiceImpl;
import com.cgi.java.FilRouge.service.MetricServiceImpl;
import com.cgi.java.FilRouge.service.ParameterServiceImpl;
import com.cgi.java.FilRouge.service.ProjectServiceImpl;
import com.cgi.java.FilRouge.service.QuoteServiceImpl;
import com.cgi.java.FilRouge.service.TeamServiceImpl;
import com.cgi.java.FilRouge.service.dto.QuoteFormServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import net.minidev.json.JSONObject;


@RestController
@RequestMapping("/devis")
public class ApiQuoteController {


	
	@Autowired
	ProjectServiceImpl projectServiceImpl;
	@Autowired
	ContractServiceImpl contractServiceImpl;
	@Autowired
	AbacusServiceImpl abacusServiceImpl;
	@Autowired
	ParameterServiceImpl parameterServiceImpl;
	@Autowired
	QuoteServiceImpl quoteServiceImpl;
	@Autowired
	MetricServiceImpl metricServiceImpl;
	@Autowired
	QuoteFormServiceImpl quoteFormServiceImpl;
	@Autowired
	QuoteDtoMapper quoteMapper;
	@Autowired
	TeamServiceImpl teamServiceImpl;


	@GetMapping(value={"/creation/api/projects/byContract/{contractId}"}, produces = "application/json")
    public String findProjectsByContract(@PathVariable("contractId") int contractId, Model model) {
	
		Contract contract = contractServiceImpl.findById(contractId);
		List<Project> projects = projectServiceImpl.findDistinctByContract(contract);
		SimpleModule module = new SimpleModule();
		module.addSerializer(Project.class, new ProjectSerializer());
		ObjectMapper map = new ObjectMapper();
		map.registerModule(module);
		String result = null;
		try {
			result = map.writeValueAsString(projects);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
		
		/*
		 * "status"=>"ok",
            "message"=>"envoi reussi",
            "data" => $countries
		 */
    }

	
	@GetMapping(value={"/creation/api/projects/byTeam/{teamId}"}, produces = "application/json")
    public String findProjectsByTeam(@PathVariable("teamId") ArrayList<Team> teamsId, Model model) {
		List<Project> projects = projectServiceImpl.findDistinctByTeams(teamsId);
		SimpleModule module = new SimpleModule();
		module.addSerializer(Project.class, new ProjectSerializer());
		ObjectMapper map = new ObjectMapper();
		map.registerModule(module);
		String result = null;
		try {
			result = map.writeValueAsString(projects);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
		
		/*
		 * "status"=>"ok",
            "message"=>"envoi reussi",
            "data" => $countries
		 */
    }
	
	
	@GetMapping(value={"/creation/api/teams/byContract/{contractId}"}, produces = "application/json")
    public String findTeamssByContract(@PathVariable("contractId") int contractId, Model model) {
	
		Contract contract = contractServiceImpl.findById(contractId);
		List<Team> teams = teamServiceImpl.findByContract(contract);
		SimpleModule module = new SimpleModule();
		module.addSerializer(Team.class, new TeamSerializer());
		ObjectMapper map = new ObjectMapper();
		map.registerModule(module);
		String result = null;
		try {
			result = map.writeValueAsString(teams);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
		
		/*
		 * "status"=>"ok",
            "message"=>"envoi reussi",
            "data" => $countries
		 */
    }	
	
	
	@GetMapping(value={"/creation/api/teams/byProject/{projectId}"}, produces = "application/json")
    public String findTeamssByProject(@PathVariable("projectId") int projectId, Model model) {
	
		Project project = projectServiceImpl.findById(projectId);
		List<Team> teams = teamServiceImpl.findByProject(project);
		SimpleModule module = new SimpleModule();
		module.addSerializer(Team.class, new TeamSerializer());
		ObjectMapper map = new ObjectMapper();
		map.registerModule(module);
		String result = null;
		try {
			result = map.writeValueAsString(teams);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
		
		/*
		 * "status"=>"ok",
            "message"=>"envoi reussi",
            "data" => $countries
		 */
    }	
	
		
	@GetMapping(value={"/creation/api/abaci/contract/{contractId}"}, produces = "application/json")
    public String findAbaciByContract(@PathVariable("contractId") int contractId, Model model) {
	
		Contract contract = contractServiceImpl.findById(contractId);
		List<Abacus> abacii = abacusServiceImpl.findUsablesAbaciiByContract(contract);
		SimpleModule module = new SimpleModule();
		//err
		module.addSerializer(Abacus.class, new AbacusSerializer());
		
		ObjectMapper map = new ObjectMapper();
		map.registerModule(module);
		String result = null;
		try {
			result = map.writeValueAsString(abacii);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
		
		/*
		 * "status"=>"ok",
            "message"=>"envoi reussi",
            "data" => $countries
		 */
    }
	
	@GetMapping(value={"/creation/api/metricTables/{metricTableId}"}, produces = "application/json")
    public String findMetricTableById(@PathVariable("metricTableId") int metricTableId, Model model) {
	
		Abacus abacus = abacusServiceImpl.findById(metricTableId);
	
		SimpleModule module = new SimpleModule();
		//err
		module.addSerializer(Abacus.class, new AbacusSerializer());
		
		
		ObjectMapper map = new ObjectMapper();
		map.registerModule(module);
		String result = null;
		try {
			result = map.writeValueAsString(abacus);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
		/*
		 * "status"=>"ok",
            "message"=>"envoi reussi",
            "data" => $countries
		 */
    }
	
	@GetMapping(value={"/creation/api/parameters/{abacusId}"}, produces = "application/json")
    public String findParametersFromAbacus(@PathVariable("abacusId") int abacusId, Model model) {
	
		Abacus abacus = abacusServiceImpl.findById(abacusId);
		List<Parameter> parameters = parameterServiceImpl.findByAbacus(abacus);
		SimpleModule module = new SimpleModule();

		module.addSerializer(Parameter.class, new ParameterSerializer());		
		ObjectMapper map = new ObjectMapper();
		map.registerModule(module);
		String result = null;
		try {
			result = map.writeValueAsString(parameters);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
		
		/*
		 * "status"=>"ok",
            "message"=>"envoi reussi",
            "data" => $countries
		 */
    }

	
	@GetMapping( value = "/api/{quoteid}" )
	public ResponseEntity<?> findDevisById(@PathVariable("quoteid") int id) {
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleModule module = new SimpleModule();
		//err
		module.addSerializer(Quote.class, new QuoteSerializer());
		mapper.registerModule(module);
		
		Quote q = quoteServiceImpl.findById(id);;
//		try {
//			q = quoteServiceImpl.findById(id);
//		}catch(NoSuchElementException ee) {
////			return "nothing here";
//		}
		String jsonDataString = "";
		try {
			jsonDataString = mapper.writeValueAsString(q);
			
			//	deserialize validation
			Quote q1 = mapper.readValue(jsonDataString, Quote.class);
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  new ResponseEntity<Quote>(q, org.springframework.http.HttpStatus.FOUND);
	}
	

	@GetMapping(value={"/creation/api/whiteQuote"}, produces = "application/json")
    public String returnWhiteQuote(Model model) {
		//Quote Qt0 = new Quote(null, null, ss1, null, null, Dcv1, null, null);
		Quote Qt0 = new Quote(null, null, null, EnumStatus.INPROGRESS, null, null, null, null);

		ObjectMapper map = new ObjectMapper();
		String result = null;
		try {
			result = map.writeValueAsString(Qt0);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
		
		/*
		 * "status"=>"ok",
            "message"=>"envoi reussi",
            "data" => $countries
		 */
    }
	

	@GetMapping(value={"/api/recherche", "/api/recherche/"}, produces="application/json")
    public String search(@RequestParam("q") String q) throws JsonProcessingException {
		List<Quote> quotes = quoteServiceImpl.findBySearchQuery(q);
		List<Project> projects = projectServiceImpl.findBySearchQuery(q);
		
		List<List<?>> search = new ArrayList<List<?>>();
		search.add(quotes);
		search.add(projects);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleModule module = new SimpleModule();
		//err
		module.addSerializer(Quote.class, new SearchQuoteSerializer());
		module.addSerializer(Project.class, new SearchProjectSerializer());
		mapper.registerModule(module);
		
		String jsonDataString = mapper.writeValueAsString(search);
		
		return jsonDataString;
        
    }

	@GetMapping(value={"/api/modulesfields/{abacusId}"}, produces = "application/json")
    public JSONObject findDistinctModulesfieldsValues(@PathVariable("abacusId") int abacusId, Model model) {
		return metricServiceImpl.findDistinctValuesFromColumnAndAbacus(abacusId);
		
    }
	

	@GetMapping(value={"/api/metric/{abacusId}/{complexity}/{interventionLvl}/{task}/{type}/{typology}"}, produces = "application/json")
    public String findMetric(@PathVariable("abacusId") int abacusId,@PathVariable("complexity") String complexity,@PathVariable("interventionLvl") String interventionLvl,@PathVariable("task") String task,@PathVariable("type") String type,@PathVariable("typology") String typology, Model model) {

		ObjectMapper mapper = new ObjectMapper();
		String result="";
		SimpleModule module = new SimpleModule();
		//err
		module.addSerializer(Metric.class, new MetricSerializer());
		mapper.registerModule(module);
		try {
			result = mapper.writeValueAsString(metricServiceImpl.findTheMetric(complexity, interventionLvl,task,type,typology, abacusId));

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result ;
		
    }
	
	@GetMapping(value={"/api/parameter/{abacusId}/{metricId}"}, produces = "application/json")
    public String findParameter(@PathVariable("abacusId") int abacusId,@PathVariable("metricId") int metricId, Model model) {
		
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		//err
		module.addSerializer(Parameter.class, new ParameterSerializer());
		mapper.registerModule(module);
		String result="";
		try {
			result = mapper.writeValueAsString(parameterServiceImpl.findTheParameter(abacusId, metricId));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
		
    }
	
	@PostMapping(value={"/save", "/save/"})
    public String saveQuote (@RequestParam("id") int quoteId,@RequestParam("name") String quoteName,@RequestParam("description") String quoteDescription,
    			@RequestParam("phases") String phases, @RequestParam("modules") String modules, @RequestParam("deletedPhases") String deletedPhases,
    			@RequestParam("deletedModules") String deletedModules,@RequestParam("idDivView") int idDivView ) {

		QuoteFormDto quoteFormIn = quoteServiceImpl.findAndMakeAsDto(quoteId);
		QuoteFormDto quoteFormOut = quoteFormServiceImpl.modifyDtoRequest(quoteFormIn, quoteName, quoteDescription, phases, modules, deletedPhases, deletedModules);
		Quote quote= quoteMapper.toEntity(quoteFormOut);
		quoteServiceImpl.setTotalCalculsFromQuote(quote);

		quoteServiceImpl.save(quote);
		return "ok";
    }
	
}