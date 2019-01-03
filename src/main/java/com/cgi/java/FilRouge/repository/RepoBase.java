package com.cgi.java.FilRouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepoBase<T, K> extends JpaRepository<T, K> {
	//void refresh();
}
