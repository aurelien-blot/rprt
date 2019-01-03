package com.cgi.java.FilRouge.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgi.java.FilRouge.model.User;

@Controller
@RequestMapping("/administration/teams")

public class AdminTeamsController {
	
	
	@GetMapping(value={"", "/"})
	public String adminTeamsList(Model model) {

		boolean userFragment = true;
		model.addAttribute("userFragment", userFragment);
	    return "/admin/teamsAdmin";
	}
}
