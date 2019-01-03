package com.cgi.java.FilRouge.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Agency;


@Repository
public interface  AgencyRepo extends JpaRepository<Agency, Integer> {
	
	Agency findById(int id);

}
