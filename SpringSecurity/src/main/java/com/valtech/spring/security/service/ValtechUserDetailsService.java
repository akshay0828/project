package com.valtech.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.valtech.spring.security.entity.User;
import com.valtech.spring.security.repository.UserReopsitory;


@Service
public class ValtechUserDetailsService implements UserDetailsService,UserDetailsManager {

	@Autowired
	private UserReopsitory userRepository;
	
	
	public void  resetUser() {
		userRepository.deleteAll();
		
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		return userRepository.findByUsername(username);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		
	}

	@Override
	public void createUser(UserDetails user) {
		userRepository.save((User)user);
	}

	@Override
	public void deleteUser(String username) {
		userRepository.delete(userRepository.findByUsername(username));
	}

	@Override
	public void updateUser(UserDetails user) {
		userRepository.save((User)user);
		
	}

	@Override
	public boolean userExists(String username) {
		return false;
	}

}
