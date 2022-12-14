package com.valtech.spring.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String username;
	private String pass;
	private String cnfmpass;
	private String address;
	private String contact;
	private boolean enabled;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	@Column(name = "role_name")

	private List<String> roles;

	public User() {

		roles = new ArrayList<>();
	}
	
	
	
	
	

	public User(String name, String email, String username, String pass, String cnfmpass, String address, String contact,
			boolean enabled, List<String> roles) {
		super();
		this.name = name;
		this.email = email;
		this.username=username;
		this.pass = pass;
		this.cnfmpass = cnfmpass;
		this.address = address;
		this.contact = contact;
		this.enabled = enabled;
		this.roles = roles;
	}






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCnfmpass() {
		return cnfmpass;
	}

	public void setCnfmpass(String cnfmpass) {
		this.cnfmpass = cnfmpass;
	}

	public String getAddress() {
		return address;
	}
	
	

	public void setUsername(String username) {
		this.username = username;
	}






	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return enabled;
	}




	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
