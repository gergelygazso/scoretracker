package com.gergelygazso.scoretracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder{
	
	
	@Bean //this annotation makes sure that we can use bCryptPasswordEncoder class in an other class. It will be dependency injected by Spring
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
