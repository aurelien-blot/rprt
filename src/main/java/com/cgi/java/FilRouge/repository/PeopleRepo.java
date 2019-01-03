package com.cgi.java.FilRouge.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.People;



@Repository
public interface PeopleRepo extends JpaRepository<People, Integer> {


}
