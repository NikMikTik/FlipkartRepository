package com.flipkart.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().logout().disable().authorizeRequests()
				.antMatchers("/flipkart/*", "/flipkart/signUp","/flipkart/allMerchants").permitAll().and().authorizeRequests().anyRequest()
				.authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/flipkart/*", "/flipkart/signUp","/flipkart/allMerchants");
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
