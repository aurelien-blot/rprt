package com.cgi.java.FilRouge.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.User;


@Repository
public interface UserRepo extends JpaRepository< User, Integer> {
	

	User findByUsername(String username);
	List<User> findByIsActiveTrue();


}
