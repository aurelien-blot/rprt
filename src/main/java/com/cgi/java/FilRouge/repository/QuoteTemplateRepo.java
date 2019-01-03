package com.cgi.java.FilRouge.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.QuoteTemplate;


@Repository
public interface QuoteTemplateRepo extends JpaRepository<QuoteTemplate, Integer> {
	


}
