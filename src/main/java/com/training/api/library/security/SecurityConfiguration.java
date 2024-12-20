package com.training.api.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.training.api.library.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private UserService userService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(config -> {
			config.requestMatchers(HttpMethod.POST, "/api/register").anonymous();
			
			config.requestMatchers(HttpMethod.GET, "/api/books","/api/books/**","/api/loans").hasRole("USER");
			
			config.requestMatchers(HttpMethod.GET, "/api/books", "/api/users").hasRole("ADMIN");
			config.requestMatchers(HttpMethod.PUT, "/api/books").hasRole("ADMIN");
			config.requestMatchers(HttpMethod.POST, "/api/books").hasRole("ADMIN");
			config.requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN");
		})
		
		.csrf(csrf -> csrf.disable())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set stateless
		.httpBasic(httpBasic -> {})// Enable Basic Auth
		.build();
	}
	
	@Bean // in memory users (temporary)
	public UserDetailsService userDetailsService() {
		return userService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}
