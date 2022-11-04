package com.valtech.spring.security.model;

public class RegisterUserModel {
	
	private String username;
	private String password;
	private String cnfmPassword;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCnfmPassword() {
		return cnfmPassword;
	}
	public void setCnfmPassword(String cnfmPassword) {
		this.cnfmPassword = cnfmPassword;
	}

}
