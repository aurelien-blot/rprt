package com.cgi.java.FilRouge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.ContractServiceImpl;
import com.cgi.java.FilRouge.service.ProjectServiceImpl;
import com.cgi.java.FilRouge.service.TeamServiceImpl;

@Controller
@RequestMapping("/administration/projects")

public class AdminProjectsController {
	
	@Autowired
	ProjectServiceImpl projectServiceImpl;
	
	@Autowired
	ContractServiceImpl contractServiceImpl;
	
	@Autowired
	TeamServiceImpl teamServiceImpl;
	
	@GetMapping(value={"", "/"})
	public String adminProjectsList(Model model) {
		List<Project> projects = projectServiceImpl.findAll();
		
		model.addAttribute("projects", projects);
	    return "/admin/projectsAdmin";
	}
	
	
	@GetMapping(value={"/archive/{projectId}"})
	public ModelAndView archiveProject(Model model, @PathVariable("projectId") int projectId) {
		
		Project project= projectServiceImpl.findById(projectId);
		project.setArchived(true);
		projectServiceImpl.save(project);
		return new ModelAndView("redirect:/administration/projects");
	}
	
	
	@GetMapping(value={"/create", "/create/"})
	public String createProject(Model model) {
		List<Contract> contracts = contractServiceImpl.findAll();
		List<Team> teams = teamServiceImpl.findAll();
		Project project = new Project();
		model.addAttribute("project", project);
		model.addAttribute("contracts", contracts);
		model.addAttribute("teams", teams);
		model.addAttribute("readOnly", false);
		model.addAttribute("create", true);
		
	    return "/admin/projectAdmin";
	}
	
	@GetMapping(value={"/modif/{projectId}"})
	public String modifProject(@PathVariable("projectId") int projectId, Model model) {
		Project project= projectServiceImpl.findById(projectId);
		
		List<Contract> contracts = contractServiceImpl.findAll();
		List<Team> teams = teamServiceImpl.findAll();
		model.addAttribute("contracts", contracts);
		model.addAttribute("teams", teams);
		model.addAttribute("project", project);
		model.addAttribute("readOnly", false);
		model.addAttribute("create", false);
	
		
	    return "/admin/projectAdmin";
	}
	

	
	@GetMapping(value={"/consult/{projectId}"})
	public String consultProject(@PathVariable("projectId") int projectId, Model model) {
		
		Project project= projectServiceImpl.findById(projectId);
		model.addAttribute("project", project);
		model.addAttribute("readOnly", true);
		model.addAttribute("create", false);
		   
			 return "/admin/projectAdmin";
	}
	
	
	@PostMapping(value={"/save/"})
	public ModelAndView saveProject(@ModelAttribute Project project, @RequestParam(value = "teams" , required = false) int[] teamsId, BindingResult result, Model model,ModelMap modelMap) {

		projectServiceImpl.save(project);
		teamServiceImpl.cleanTeamFromOneProject(project);
		for(int id : teamsId) {
			teamServiceImpl.addProject(teamServiceImpl.findById(id), project);
		}
		
	
		return new ModelAndView("redirect:/administration/projects");
	}
	
}
