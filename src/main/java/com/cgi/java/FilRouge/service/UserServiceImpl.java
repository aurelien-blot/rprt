package com.cgi.java.FilRouge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.repository.UserRepo;
import com.cgi.java.FilRouge.service.base.BaseService;

@Service
public class UserServiceImpl extends BaseService<UserRepo, User> {	
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public User findByUsername(String username){
		User user = this.repository.findByUsername(username);
		
		return user;
	}
	
	public List<User> findByIsActiveTrue(){
		List<User> result = this.repository.findByIsActiveTrue();
		
		return result;
	}

	public void changeUserPassword(User user, String newPassword) {
		
			user.setPassword(bCryptPasswordEncoder.encode(newPassword));
			this.save(user);
	}
	
	public boolean controlPasswordForm(User user, String previousPwd, String pwd1, String pwd2) {
		
		if(bCryptPasswordEncoder.matches(previousPwd, user.getPassword()) && pwd1.equals(pwd2) && !previousPwd.equals("") && !pwd1.equals("") && !pwd2.equals("")){
			return true;
		}
		return false;
	}
	
	
	
}