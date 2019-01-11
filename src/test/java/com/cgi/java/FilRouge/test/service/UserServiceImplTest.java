package com.cgi.java.FilRouge.test.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cgi.java.FilRouge.model.Role;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.UserServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Mock
	UserServiceImpl userServiceImpl;
	
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
		
		user.setPassword(bCryptPasswordEncoder.encode("test"));
		
		// bCryptPasswordEncoder.matches(previousPwd, user.getPassword())
		assertThat(bCryptPasswordEncoder.matches("test", user.getPassword())).isTrue();
		//assertThat(userServiceImpl.controlPasswordForm(user, "test", "test2", "test2")).isTrue();
		//assertTrue("Contrôle du formulaire de mot de passe : ok",userServiceImpl.controlPasswordForm(user, "test", "test2", "test2"));
		
		
	}
	
	
	@Test
	public void testSetPassword() {
		user.setPassword(bCryptPasswordEncoder.encode(User.genericPassword));	
		assertThat(bCryptPasswordEncoder.matches(User.genericPassword, user.getPassword())).isTrue();
		assertThat(bCryptPasswordEncoder.matches("cgicgicgi", user.getPassword())).isFalse();
		assertThat(User.genericPassword).isNotEqualTo(bCryptPasswordEncoder.encode(User.genericPassword));
	}
	
	
	
	
	
	@Test
	
	public void testSave() {
		
		
		
		assertThat(userDataBase.getLastname()).isEqualTo("lastname");
		//userServiceImpl.delete(userDataBase.getId());
		
	}
	
	
	@Before
	public void setUp() {
		User userTest = new User();
		userTest.setUsername("username");
		userTest.setFirstname("firstname");
		userTest.setActive(true);
		userTest.setLastname("lastname");
		userTest.setStaffNumber("staff_number");
		
		Mockito.when(userServiceImpl.findByUsername(userTest.getUsername())).thenReturn(userTest);
	}


}
