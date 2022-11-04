package com.valtech.spring.security.jtw.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	@GetMapping("/hello")
	public String hello(){
		return "Hello ";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String admin(){
		return "Hello admin";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String user(){
		return "Hello User";
	}

}
