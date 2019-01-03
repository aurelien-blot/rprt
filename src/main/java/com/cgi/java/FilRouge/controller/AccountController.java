package com.cgi.java.FilRouge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.UserServiceImpl;

@Controller
@RequestMapping("/mon-compte")
public class AccountController {
	
	
	@Autowired
	UserServiceImpl userServiceImpl;
	 
	@GetMapping(value={"", "/"})
	public String search() {
	    return "/account/account";
	}
	
	@GetMapping(value={"/password/change", "/password/change/"})
	public String changePassword() {
	    return "/account/changePassword";
	}
	
	@PostMapping(value={"/password/change", "/password/change/"})
	public String validateNewPassword(@RequestParam("previousPwd") String previousPwd,@RequestParam("newPwd1") String newPwd1, @RequestParam("newPwd2") String newPwd2, Authentication auth) {
		
		
		if( auth != null ) {
			User user = userServiceImpl.findByUsername(auth.getName());
			if(userServiceImpl.controlPasswordForm(user, previousPwd, newPwd1, newPwd2)) {
				userServiceImpl.changeUserPassword(user, newPwd1);
				return "/account/account";
			}
		}
		

	    return "/error";
	}

}
