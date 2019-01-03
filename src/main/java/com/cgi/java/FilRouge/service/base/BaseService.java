package com.cgi.java.FilRouge.service.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.context.WebApplicationContext;

import com.cgi.java.FilRouge.model.People;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.repository.QuoteRepo;

/**
 * Ce service abstrait injecte le repository correspondant à la classe de l'entité T
 * Il définit des méthodes de base de tous les services
 * Les implémentations de ce Service pourront porter des méthodes répondant à des nécéssités métier particulières
 * */
public abstract class BaseService<REPO extends JpaRepository<C, Integer>, C> {
		
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Le repository correspondant à la bonne entité sera automatiquement injecté
	 * */
	@Autowired
	protected REPO repository;

	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public C delete(final Integer id) throws EntityNotFoundException{
		final C deleted = this.repository.findById(id).get();
		
		if( deleted == null ) {
			throw new EntityNotFoundException();
		}
		this.repository.delete(deleted);
		
		return deleted;
	}
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public C findById(final Integer id) throws EntityNotFoundException{
		final C thing = this.repository.findById(id).get();
		if( thing == null ) {
			throw new EntityNotFoundException();
		}
	
		return thing;
		
	}
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public List<C> findAll() throws EntityNotFoundException{
		final List<C> thing = this.repository.findAll();
		if( thing == null ) {
			throw new EntityNotFoundException();
		}
		return thing;
	}
	
	@Transactional(rollbackOn = EntityNotFoundException.class)
	public void save(C thing) throws EntityNotFoundException{
		this.repository.save(thing);
		this.repository.flush();
	}
	
	@Transactional(rollbackOn = IllegalArgumentException.class)
	public void delete(C thing) throws IllegalArgumentException{
		this.repository.delete(thing);
		this.repository.flush();
	}
		
}