package com.cgi.java.FilRouge.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.UserServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserServiceImpl userServiceImpl;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Spy
	User user = new User();
	
	
	@Test
	public void testFindByUsername() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testFindByIsActiveTrue() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeUserPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testControlPasswordForm() {
		
		
		System.out.println(userServiceImpl.controlPasswordForm(user, "test", "test2", "test2"));

		assertTrue("Contrôle du formulaire de mot de passe : ok",userServiceImpl.controlPasswordForm(user, "test", "test2", "test2"));
		//assertThat(1).isEqualTo(1);
		
	}

}
