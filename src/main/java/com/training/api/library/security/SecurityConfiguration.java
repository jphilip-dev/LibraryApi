package com.training.api.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(config -> {
			config.requestMatchers(HttpMethod.GET, "/api/books","/api/books/**").hasRole("USER");
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
		UserDetails normalUser = User.builder()
				.username("gc")
				.password("$2a$12$ouJ3lObZoFtlvi8ObOUryeRBZ..0HiHqhtPNIbAFX.oIamCnb83mS") // 1234
				.roles("USER")
				.build();
		
		UserDetails adminUser = User.builder()
				.username("admin")
				.password("$2a$12$ouJ3lObZoFtlvi8ObOUryeRBZ..0HiHqhtPNIbAFX.oIamCnb83mS") // 1234
				.roles("ADMIN","USER")
				.build();
		return new InMemoryUserDetailsManager(normalUser,adminUser);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
