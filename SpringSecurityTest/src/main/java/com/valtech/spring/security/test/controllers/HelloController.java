package com.valtech.spring.security.test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(){
		return "Hello";
	}
	
	@GetMapping("/admin")
	public String admin(){
		return "Hello admin";
	}
	
	@GetMapping("/user")
	public String user(){
		return "Hello User";
	}

}
