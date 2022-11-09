package com.valtech.spring.security.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.valtech.spring.security.entity.User;
import com.valtech.spring.security.repo.UserReopsitory;



@Service
public class ValtechUserDetailsService  {

	@Autowired
	private UserReopsitory userRepository;
	
	
	public void  resetUser() {
		userRepository.deleteAll();
		
	}
	
	public void createUser(User user) {
		userRepository.save(user);
	}
	
	

	
	
	
	
 public int LoginValidator(String username) {
		
	 if(userRepository.findByUsername(username)!=null){
		 return 1;
	 }
	 
	 
		return 0;
		
		
	}
	

}
