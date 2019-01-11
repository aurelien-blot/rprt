package com.cgi.java.FilRouge.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.UserServiceImpl;

@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@SpringBootTest
public class UserDataJpaTest {

	@Autowired
	UserServiceImpl userServiceImpl;
	

	@Test
	public void save() {
		User userTest = new User();
		userTest.setUsername("username");
		userTest.setFirstname("firstname");
		userTest.setActive(true);
		userTest.setLastname("lastname");
		userTest.setStaffNumber("staff_number");
		userServiceImpl.save(userTest);
		User userDataBase = userServiceImpl.findByUsername("username");
		
		assertThat(userDataBase.getLastname()).isEqualTo("lastname");
	}

}
