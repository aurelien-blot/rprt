package com.cgi.java.FilRouge.service.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.model.Phase;

//@Service
public class SearchService {
	
	/*@Autowired
    private final EntityManager centityManager;

    @Autowired
    public SearchService(EntityManager entityManager) {
        super();
        this.centityManager = entityManager;
    }


    public void initializeHibernateSearch() {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public List<Phase> phaseSearch(String search){
    	 FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
         QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Phase.class).get();
         Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("name")
                 .matching(search).createQuery();

         javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Phase.class);

         // execute search

         List<Phase> phaseList = null;
         try {
        	 phaseList = jpaQuery.getResultList();
         } catch (NoResultException nre) {
             ;// do nothing

         }
    	
    	
    	return phaseList;
    }*/
    
}
