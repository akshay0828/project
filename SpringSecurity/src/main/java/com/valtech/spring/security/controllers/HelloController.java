package com.valtech.spring.security.controllers;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valtech.spring.security.model.RegisterUserModel;
import com.valtech.spring.security.service.ValtechUserDetailsService;




@Controller
public class HelloController {
	
	
	@Autowired 
private	ValtechUserDetailsService userDetailsManager;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/resetUsers")
	@ResponseBody
	public String resetUser(){
		
		System.out.println(userDetailsManager.loadUserByUsername("scott"));
		System.out.println(userDetailsManager.loadUserByUsername("admin"));
		userDetailsManager.resetUser();

		com.valtech.spring.security.entity.User user =new com.valtech.spring.security.entity.User("scott",passwordEncoder.encode("tiger"));
		user.setRoles(Arrays.asList("USER"));
		user.setEnabled(true);
		userDetailsManager.createUser(user);
		
		
		com.valtech.spring.security.entity.User admin =new com.valtech.spring.security.entity.User("admin",passwordEncoder.encode("admin"));
		admin.setRoles(Arrays.asList("USER","ADMIN"));
		admin.setEnabled(true);
		userDetailsManager.createUser(admin);
		
		
		return "Success";
		
	}
	
	@GetMapping("/register")
	//@ResponseBody
	public String register() {
		return "register";
		
	}
	
	@GetMapping("/login")

	public String login() {
		return "login";
		
	}
	
	@PostMapping("/register")
	//@ResponseBody
	public String registerUser(@ModelAttribute RegisterUserModel registerUserModel) {
		
		
		if(registerUserModel.getPassword().equals(registerUserModel.getCnfmPassword())){
			
			userDetailsManager.createUser(User.withUsername(registerUserModel.getUsername()).password(registerUserModel.getPassword()).roles("USER").build());
			return "login";
			
		}else{
		return "register";
		}
		
	}
	
	
	@GetMapping("/user")
	//@ResponseBody
	public String userPage() {
		return "user/home";
		
	}
	
	@GetMapping("/admin")
	//@ResponseBody
	public String adminPage() {
		return "admin/home";
	}
	
	@GetMapping("/")
	@ResponseBody
	public String index(){
		return "Success";
		
	}
	
	
	
	

}
