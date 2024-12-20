package com.training.api.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.api.library.entity.User;
import com.training.api.library.repository.UserRepository;


@Service
public class UserService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findById(username);
		
		if (user.isEmpty() ) {
		    throw new UsernameNotFoundException(username);
		}
		
		if (!user.get().getStatus()) {
			throw new IllegalArgumentException(username + " not Activated");
		}

	
		
		return org.springframework.security.core.userdetails.User.builder()
					.username(user.get().getUsername())
					.password(user.get().getPassword())
					.roles(getRoles(user.get()))
					.build();
	}
	
	private String[] getRoles(User userObj) {
	    return userObj.getRoles()
	                  .stream()
	                  .map(r -> r.getRole()) // Lambda used here
	                  .toArray(String[]::new); // Convert to a String array
	}
	
	public User addUser(User user) {
		Optional<User> dbUser = userRepository.findById(user.getUsername());
		
		if (dbUser.isPresent()) {
			throw new IllegalArgumentException("Username " + user.getUsername() + " already exist" );
		}
		
		return userRepository.save(user);
	}
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}


}
