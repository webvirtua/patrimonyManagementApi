package com.webvirtua.patrimony.app.config;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter
//{
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception 
//	{
//		httpSecurity.csrf().disable().authorizeRequests()
//			.antMatchers(HttpMethod.POST, "/v1/clients").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.httpBasic()
//			.and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
//	{
//		auth.inMemoryAuthentication()
//			.withUser("luiz").password("123").roles("ADMIN");
//	}
//}
