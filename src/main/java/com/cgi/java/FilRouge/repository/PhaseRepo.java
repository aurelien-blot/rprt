package com.cgi.java.FilRouge.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cgi.java.FilRouge.model.Phase;


@Repository
public interface PhaseRepo extends JpaRepository<Phase, Integer> {
	
	@Query
	public List<Phase> findByNameLike(String q);
	
	@Query(nativeQuery = true, value="SELECT * FROM Phase p WHERE p.name like :q GROUP BY p.name")
	public List<Phase> findDistinctByNameLike(String q);
		
}
