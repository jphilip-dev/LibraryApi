package com.training.api.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.api.library.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
}
