package com.cgi.java.FilRouge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.repository.PhaseRepo;
import com.cgi.java.FilRouge.service.base.BaseService;

@Service
public class PhaseServiceImpl extends BaseService<PhaseRepo, Phase> {

	public List<Phase> findByLikeQuery(String query){
		List<Phase> phases = this.repository.findByNameLike(query);
		return phases;
	}
	
	public List<Phase> findDistinctByLikeQuery(String query){
		List<Phase> phases = this.repository.findDistinctByNameLike(query);
		return phases;
	}
	
}
