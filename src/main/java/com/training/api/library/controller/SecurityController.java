package com.training.api.library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.api.library.entity.User;
import com.training.api.library.entity.dto.UserDTO;
import com.training.api.library.entity.dto.UserMapper;
import com.training.api.library.service.UserService;

@RestController
public class SecurityController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/api/register")
	public UserDTO addUser(@RequestBody User user) {
		user.addRole("USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userService.addUser(user);
		return UserMapper.toDTO(savedUser);
	}
	
	@GetMapping("/api/users")
	public List<UserDTO> allUsers(){
		List<User> users = userService.getAllUser();
		return users.stream()
					.map(u -> UserMapper.toDTO(u))
					.collect(Collectors.toList());
	}
}
