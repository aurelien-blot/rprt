package com.cgi.java.FilRouge.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Contract;


@Repository
public interface AbacusRepo extends JpaRepository<Abacus, Integer> {
	
	@Query(nativeQuery = true, value="SELECT * FROM abacus WHERE contract= ?1 and is_finished and !is_archived")
	public List<Abacus> findUsablesAbaciiByContract(Contract contract);

	
	public List<Abacus> findByContract(Contract contract);

}
