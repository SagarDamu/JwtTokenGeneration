package com.smi.jwt.springsecurity.jwtspringsecuritytask.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smi.jwt.springsecurity.jwtspringsecuritytask.model.ApplicationUsers;
import com.smi.jwt.springsecurity.jwtspringsecuritytask.repository.ApplicationUsersRepository;


@Service
public class CustomerUserDetailService implements UserDetailsService {

	@Autowired
	private ApplicationUsersRepository aup;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUsers users = aup.findByuserName(username);
		return new User(users.getUserName(), users.getPassword(), new ArrayList<GrantedAuthority>());
	}
	
}
