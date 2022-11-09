package com.valtech.spring.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valtech.spring.security.entity.User;
import com.valtech.spring.security.model.RegisterUserModel;
import com.valtech.spring.security.service.ValtechUserDetailsService;

//@RestController
@Controller
public class HelloController {
	
	@Autowired
	private ValtechUserDetailsService service;
	
	@GetMapping("/register")
	public String register(){
		return "register";
	}
	
	
	@PostMapping("/register")
	//@ResponseBody
	public String registerUser(@ModelAttribute User user) {
		
		
		//if(User.h){
			service.createUser(user);
			//service.createUser(User.withUsername(registerUserModel.getEmail()).password(registerUserModel.getPass()).roles("USER").build());
			return "login";
			
		//}else{
		//return "register";
		//}
		
	}
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	
	@PostMapping("/login")
	//@ResponseBody
	public String loginUser(@ModelAttribute RegisterUserModel registerUserModel){
		
		//String us=registerUserModel.getUsername();
		if(service.LoginValidator(registerUserModel.getUsername())==1){
			return "user/home";
		}
		
		return "failure";
	}
	
	
	
	@GetMapping("/seller")
	public String admin(){
		return "Hello seller";
	}
	
	@GetMapping("/index")
	public String index(){
		return "Index";
	}
	
	
	@GetMapping("/user")
	public String user(){
		return "Hello User";
	}
	
	@GetMapping("/deliveryPerson")
	public String deliveryPerson(){
		return "Hello DeliveryPerson";
	}

}
