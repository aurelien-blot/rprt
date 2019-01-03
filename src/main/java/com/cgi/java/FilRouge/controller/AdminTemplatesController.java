package com.cgi.java.FilRouge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.Role;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.RoleServiceImpl;
import com.cgi.java.FilRouge.service.UserServiceImpl;

import sun.security.util.Password;

@Controller
@RequestMapping("/administration/templates")
public class AdminTemplatesController {


	@GetMapping(value={"", "/"})
	public String adminUsersList(Model model) {
		
	    return "/admin/templatesAdmin";
	}
	
}



