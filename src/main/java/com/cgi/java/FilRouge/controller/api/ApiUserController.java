package com.cgi.java.FilRouge.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/user")
public class ApiUserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@GetMapping(value={"/api/resetpwd/{userId}"}, produces = "application/json")
    public String resetPassword(@PathVariable("userId") int userId, Model model) {
	
		User user = userServiceImpl.findById(userId);

		user.setPassword(bCryptPasswordEncoder.encode(User.genericPassword));
		userServiceImpl.save(user);

		ObjectMapper map = new ObjectMapper();
		String result = null;
		try {
			result = map.writeValueAsString("ok");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

    }
	
	@GetMapping(value={"/api/verify/username/{username}"}, produces = "application/json")
    public boolean verifyUsernameArlreadyExist(@PathVariable("username") String username, Model model) {
	
		User user = userServiceImpl.findByUsername(username);

		if(user !=null) {
			return false;
		}
		else {
			return true;
		}

    }
	
}
