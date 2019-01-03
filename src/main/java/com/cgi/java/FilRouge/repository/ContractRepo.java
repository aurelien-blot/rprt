package com.cgi.java.FilRouge.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.java.FilRouge.model.Contract;



@Repository
public interface  ContractRepo extends JpaRepository<Contract, Integer> {
	


}
