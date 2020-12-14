package com.hedspi.team45.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hedspi.team45.handler_filter.CustomAccessDeniedHandler;
import com.hedspi.team45.handler_filter.JwtAuthenticationTokenFilter;
import com.hedspi.team45.handler_filter.RestAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {

		JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
		jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
		return jwtAuthenticationTokenFilter;

	}

	@Bean
	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {

		return new RestAuthenticationEntryPoint();

	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {

		return super.authenticationManager();
	}

	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeRequests().antMatchers("/Calendar-Event/login","/Calendar-Event/logout").permitAll();
		
		httpSecurity.csrf().ignoringAntMatchers("/Calendar-Event/**");
		httpSecurity.antMatcher("/Calendar-Event/**")
				.httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()		
				.antMatchers(HttpMethod.GET,"/Calendar-Event/admin/**").access("hasRole('admin')")
				.antMatchers(HttpMethod.POST,"/Calendar-Event/admin/**").access("hasRole('admin')")
				.antMatchers(HttpMethod.PUT,"/Calendar-Event/admin/**").access("hasRole('admin')")
				.antMatchers(HttpMethod.DELETE,"/Calendar-Event/admin/**").access("hasRole('admin')")
				
				
				.antMatchers(HttpMethod.GET,"/Calendar-Event/user/**").access("hasRole('customer')")
				.antMatchers(HttpMethod.POST,"/Calendar-Event/user/**").access("hasRole('customer')")
				.antMatchers(HttpMethod.PUT,"/Calendar-Event/user/**").access("hasRole('customer')")
				.antMatchers(HttpMethod.DELETE,"/Calendar-Event/user/**").access("hasRole('customer')")
				
				
				.and()
				.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
		
	}
}
