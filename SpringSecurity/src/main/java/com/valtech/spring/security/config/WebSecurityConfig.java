package com.valtech.spring.security.config;

import javax.sql.DataSource;
import javax.annotation.security.RolesAllowed;

import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import net.bytebuddy.build.Plugin.Engine.Source.InMemory;

@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
	
	
	public void createTableIfNessary(javax.sql.DataSource datasource){
		try{
			JdbcTemplate temp=new JdbcTemplate(datasource);
			
			temp.execute("drop table authorities");
			//temp.execute("drop table users");
			
			
			temp.execute("create table authorities (username varchar(60),authority varchar(60))");
			
			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		
	}

	@Bean
	public UserDetailsManager	 userDetailsManager(PasswordEncoder passwordEncoder , javax.sql.DataSource datasource) {
		
		//InMemoryUserDetailsManager manager =new InMemoryUserDetailsManager();
		
		JdbcUserDetailsManager manager= new JdbcUserDetailsManager(datasource);
		
		createTableIfNessary(datasource);
		
		manager.createUser(User.withUsername("scott").password(passwordEncoder.encode("tiger")).roles("USER").build());
		manager.createUser(User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER","ADMIN").build());
		return manager;
		
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception{
		http.csrf().disable().authorizeHttpRequests()
//		.antMatchers("/","/user/**").hasAnyRole("USER")
//		.antMatchers("/admin/**").hasAnyRole("ADMIN")
//		.antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
		.antMatchers("/register/","/login","/logout","/resetUsers").permitAll()
		.anyRequest().authenticated();
		//http.httpBasic();
		http.formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login");
		return http.build();
		
	
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
