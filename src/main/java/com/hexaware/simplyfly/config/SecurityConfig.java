package com.hexaware.simplyfly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.hexaware.simplyfly.filter.JwtAuthFilter;
import com.hexaware.simplyfly.service.UserInfoUserDetailService;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter authFilter;
	
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoUserDetailService();
	}
	
	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.cors().and().csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/simply-fly/customers/create-account","/simply-fly/admin/add-user","/simply-fly/customers/login","/simply-fly/customers/enter","/simply-fly/admin/add-admin",
						"/simply-fly/flightTrips/search-flights-by-date-source-destination/{departure}/{sourceIata}/{destinationIata}").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/simply-fly/customers/**",
						"/simply-fly/admin/**","/simply-fly/flights/**","/simply-fly/flightTrips/**").authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter	, UsernamePasswordAuthenticationFilter.class).build();
	}
	
	@Bean
	 public CorsFilter corsFilter() {
     CorsConfiguration config = new CorsConfiguration();
     config.setAllowCredentials(true);
     config.addAllowedOrigin("http://localhost:4200");
     config.addAllowedHeader("*");
     config.addAllowedMethod("*");
     
     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", config);
     
     return new CorsFilter(source);
 }
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	
	
	
}
