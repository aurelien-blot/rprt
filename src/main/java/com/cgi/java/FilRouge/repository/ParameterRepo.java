package com.cgi.java.FilRouge.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Metric;
import com.cgi.java.FilRouge.model.Parameter;


@Repository
public interface ParameterRepo extends JpaRepository<Parameter, Integer> {
	
	@Modifying
	@Query(nativeQuery = true, value="DELETE FROM parameter WHERE abacus = ?1")
	public void deleteParametersFromAbacus(int abacusId);
	
	public List<Parameter> findByAbacus(Abacus abacus);
	public List<Parameter> findByAbacusAndMetric(Abacus abacus, Metric metric);

}
