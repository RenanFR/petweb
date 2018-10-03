package com.scrum.challenge.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.defaultSuccessUrl("/index")
				.and()
				.logout()
				.permitAll();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails userDetails = User
				.withUsername("username")
				.password("password")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(Arrays.asList(userDetails));
	}

}
