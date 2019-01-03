package com.cgi.java.FilRouge.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Hypothesis;



@Repository
public interface  HypothesisRepo extends JpaRepository<Hypothesis, Integer> {
	


}
