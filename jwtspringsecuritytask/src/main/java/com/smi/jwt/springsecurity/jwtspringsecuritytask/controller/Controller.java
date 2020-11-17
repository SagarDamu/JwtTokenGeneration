package com.smi.jwt.springsecurity.jwtspringsecuritytask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smi.jwt.springsecurity.jwtspringsecuritytask.jwtutility.JwtUtility;
import com.smi.jwt.springsecurity.jwtspringsecuritytask.model.ApplicationUsers;
import com.smi.jwt.springsecurity.jwtspringsecuritytask.model.AuthModel;
import com.smi.jwt.springsecurity.jwtspringsecuritytask.repository.ApplicationUsersRepository;

@RestController
public class Controller {

	@Autowired
	private ApplicationUsersRepository apur;
	@Autowired
	private JwtUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping("/")
	public String home() {
		return "Working";
	}

	@GetMapping("/getUser")
	public List<ApplicationUsers> getUser() {
		return apur.findAll();
	}

	@PostMapping("/userRegister")
	public String userRegister(@RequestBody ApplicationUsers apu) {
		if (apur.save(apu) != null) {
			return "Registered success";
		} else {
			return "Failed";
		}
	}

	@PostMapping("/getToken")
	public String generateToken(@RequestBody AuthModel authModel) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authModel.getUserName(), authModel.getPassword()));
		} catch (Exception e) {
			throw new Exception("invalid username and password");
		}
		return jwtUtility.generateToken(authModel.getUserName());
	}

}
