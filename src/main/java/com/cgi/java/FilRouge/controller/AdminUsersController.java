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
@RequestMapping("/administration/users")
@SessionAttributes(value={"user"})
public class AdminUsersController {

	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	RoleServiceImpl roleServiceImpl;
	
	
	@GetMapping(value={"", "/"})
	public String adminUsersList(Model model, Authentication auth) {
		
		List<User> users = userServiceImpl.findByIsActiveTrue();
		int applicationUserId = userServiceImpl.findByUsername(auth.getName()).getId();
		model.addAttribute("users", users);
		model.addAttribute("applicationUserId", applicationUserId);
	    return "/admin/usersAdmin";
	}
	
	@GetMapping(value={"/view/{userId}"})
	public String viewUser(Model model, @PathVariable("userId") int userId) {
		User user = userServiceImpl.findById(userId);
		model.addAttribute("user", user);
	    return "/admin/userAdmin";
	}
	
	@GetMapping(value={"/modif/{userId}"})
	public String modifUser(Model model, @PathVariable("userId") int userId, Authentication auth) {
		
		User user = userServiceImpl.findById(userId);
		List<Role> roles = roleServiceImpl.findAll();
		int applicationUserId = userServiceImpl.findByUsername(auth.getName()).getId();
		
		model.addAttribute("genericPassword", User.genericPassword);
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		model.addAttribute("create", false);
		model.addAttribute("applicationUserId", applicationUserId);
		
	    return "/admin/userAdmin";
	}
	
	@PostMapping(value={"/save/", "/user/save"})
	public ModelAndView saveUser(@ModelAttribute User user,  BindingResult result, Model model,ModelMap modelMap) {
		
		userServiceImpl.save(user);
		
		
		return new ModelAndView("redirect:/administration/users/");
	}
	
	@GetMapping(value={"/create"})
	public String createUser(Model model,SessionStatus status, Authentication auth) {
		
		User user = new User();
		user.setPassword(bCryptPasswordEncoder.encode(User.genericPassword));
		int applicationUserId = userServiceImpl.findByUsername(auth.getName()).getId();
				
		List<Role> roles = roleServiceImpl.findAll();
		
		model.addAttribute("genericPassword", User.genericPassword);
		model.addAttribute("user", user);
		model.addAttribute("create", true);
		model.addAttribute("roles", roles);
		model.addAttribute("applicationUserId", applicationUserId);
		
	    return "/admin/userAdmin";
	}
	
	@GetMapping(value={"/delete/{userId}"})
	public ModelAndView deleteUser(Model model, @PathVariable("userId") int userId, Authentication auth) {
		
		User applicationUser = userServiceImpl.findByUsername(auth.getName());
		User user = userServiceImpl.findById(userId);
		if(applicationUser != user) {
			user.setActive(false);
		}
		
		userServiceImpl.save(user);
		
		return new ModelAndView("redirect:/administration/users/");
	}
	
}



