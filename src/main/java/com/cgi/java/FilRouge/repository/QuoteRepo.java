package com.cgi.java.FilRouge.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;


@Repository
public interface QuoteRepo extends JpaRepository< Quote, Integer> {
	Quote findById(int id);
	
	List<Quote> findByCreatedOn(Date date);
	List<Quote> findByNameLikeOrDescriptionLike(String like, String like2);
	long countByProject(Project project);
}
