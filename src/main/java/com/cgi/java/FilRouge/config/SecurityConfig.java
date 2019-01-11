package com.cgi.java.FilRouge.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.cgi.java.FilRouge.security.CustomBasicAuthenticationEntryPoint;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	
	@Override 
	public void configure(WebSecurity web) throws Exception { 
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/json/**");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		  http.csrf().disable()
          .authorizeRequests()
          		.antMatchers("/webjars/**", "/css/**", "/js/**" , "/test/**").permitAll()
				.antMatchers("/administration/**").hasAnyRole("ADMIN_VIEW")
				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN", "ADMIN_VIEW")
				.antMatchers("/ajax/**").hasRole("ADMIN")
				
				.anyRequest().authenticated()
          .and()
          .formLogin()
				.loginPage("/login").failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/",true)
				.permitAll()
				.and()
          .logout()
			.permitAll()
		;
    }
	    
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.
		jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.passwordEncoder(bCryptPasswordEncoder);
			;
    }
	
	 
	 
	 
	 /*
	     * 	Sous configuration pour l'authentification basic auth
	     * 
	     * */
	    @Configuration
	    @Order(1)
	    public static class BasicAuthSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{
	    	@Autowired
		    private AccessDeniedHandler accessDeniedHandler;
		    @Autowired
		    private DataSource dataSource;
		    @Autowired
			private BCryptPasswordEncoder bCryptPasswordEncoder;
		    @Value("${spring.queries.users-query}")
			private String usersQuery;
			@Value("${spring.queries.roles-query}")
			private String rolesQuery;
		    
	    	protected void configure(HttpSecurity http) throws Exception{
	    		http
	    		.authorizeRequests()
	            .antMatchers("/login*").permitAll()
	            .antMatchers("/api/**").authenticated()
	            .and()
	            .antMatcher("/api/**")
				.httpBasic().realmName("MY_TEST_REALM").authenticationEntryPoint(getBasicAuthEntryPoint())
				
				
				;
	    		
	    		
	    	}
	    	
//	    	http auth
	        @Bean
	    	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
	    		return new CustomBasicAuthenticationEntryPoint();
	    	}
	        
	        @Override
	        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        	auth.
	    		jdbcAuthentication().dataSource(dataSource)
	    			.usersByUsernameQuery(usersQuery)
	    			.authoritiesByUsernameQuery(rolesQuery)
	    			.passwordEncoder(bCryptPasswordEncoder);
	    			;
	        }
	        
	    }
	 
}
