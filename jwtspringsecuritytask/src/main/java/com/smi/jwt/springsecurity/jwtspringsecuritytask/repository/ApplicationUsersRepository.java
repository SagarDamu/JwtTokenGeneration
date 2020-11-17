package com.smi.jwt.springsecurity.jwtspringsecuritytask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smi.jwt.springsecurity.jwtspringsecuritytask.model.ApplicationUsers;

public interface ApplicationUsersRepository extends JpaRepository<ApplicationUsers, Integer> {

	ApplicationUsers findByuserName(String username);
	
}
