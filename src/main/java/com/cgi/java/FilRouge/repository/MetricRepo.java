package com.cgi.java.FilRouge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Metric;

@Repository
public interface MetricRepo extends JpaRepository<Metric, Integer>{

	@Query(nativeQuery = true, value="SELECT DISTINCT(complexity) FROM Metric JOIN parameter ON parameter.metric = metric.id JOIN abacus ON abacus.id = parameter.abacus WHERE abacus.id = ?1")
	public List<String> findDistinctComplexityFromAbacus(int abacusId);
	
	@Query(nativeQuery = true, value="SELECT DISTINCT(task) FROM Metric JOIN parameter ON parameter.metric = metric.id JOIN abacus ON abacus.id = parameter.abacus WHERE abacus.id = ?1")
	public List<String> findDistinctTaskFromAbacus(int abacusId);
	
	@Query(nativeQuery = true, value="SELECT DISTINCT(intervention_level) FROM Metric JOIN parameter ON parameter.metric = metric.id JOIN abacus ON abacus.id = parameter.abacus WHERE abacus.id = ?1")
	public List<String> findDistinctInterventionLvlFromAbacus(int abacusId);
	
	@Query(nativeQuery = true, value="SELECT DISTINCT(type) FROM Metric JOIN parameter ON parameter.metric = metric.id JOIN abacus ON abacus.id = parameter.abacus WHERE abacus.id = ?1")
	public List<String> findDistinctTypeFromAbacus(int abacusId);
	
	@Query(nativeQuery = true, value="SELECT DISTINCT(typology) FROM Metric JOIN parameter ON parameter.metric = metric.id JOIN abacus ON abacus.id = parameter.abacus WHERE abacus.id = ?1")
	public List<String> findDistinctTypologyFromAbacus(int abacusId);
	
	@Query(nativeQuery = true, value="SELECT * FROM Metric JOIN parameter ON parameter.metric = metric.id JOIN abacus ON abacus.id = parameter.abacus WHERE complexity =?1 AND intervention_level =?2 AND task=?3 AND type=?4 AND typology=?5 AND abacus.id=?6")
	public List<Metric> findOneMetricInAbacus(String complexity, String interventionLvl,String task,String type,String typology,int abacusId);

	@Query(nativeQuery = true, value="SELECT * FROM Metric WHERE complexity =?1 AND intervention_level =?2 AND task=?3 AND type=?4 AND typology=?5")
	public List<Metric> findAMetric(String complexity, String interventionLvl,String task,String type,String typology);

	@Query(nativeQuery = true, value="SELECT DISTINCT(typology) FROM Metric WHERE typology != ''")
	public List<String> findDistinctTypology();
	
	@Query(nativeQuery = true, value="SELECT DISTINCT(task) FROM Metric WHERE task != ''")
	public List<String> findDistinctRole();
	
	@Query(nativeQuery = true, value="SELECT DISTINCT(intervention_level) FROM Metric WHERE intervention_level != ''")
	public List<String> findDistinctInterventionLvl();
	
	@Query(nativeQuery = true, value="SELECT DISTINCT(type) FROM Metric WHERE type != ''")
	public List<String> findDistinctType();
	
	@Query(nativeQuery = true, value="SELECT DISTINCT(complexity) FROM Metric WHERE complexity != ''")
	public List<String> findDistinctComplexity();
}
