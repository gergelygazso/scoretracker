package com.gergelygazso.scoretracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gergelygazso.scoretracker.appuser.AppUserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AppUserService appUserService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //ensures that we can send POST request without rejecting. Enabled by default, so POST request need a csrf token for execution
			.authorizeRequests()
			.antMatchers("/", "/registerorlogin", "/showregistrationform", "/processregistrationform", "/home").permitAll()
			.antMatchers("/api/v*/registration/**").permitAll()
			//.antMatchers("/api/v*/matches/**").hasAnyRole("USER", "ADMIN")
			
			//.antMatchers("/**").permitAll()
			
			.anyRequest()
			.authenticated().and() //anyRequest must be authenticated and...
			.formLogin() //use form-based login
				.defaultSuccessUrl("/home",true);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider =
				new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(appUserService);
		return provider;
	}
		
}
