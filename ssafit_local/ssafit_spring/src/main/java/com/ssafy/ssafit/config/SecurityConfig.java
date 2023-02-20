package com.ssafy.ssafit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.ssafit.util.JwtAuthenticationFilter;
import com.ssafy.ssafit.util.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Bean
	public InMemoryUserDetailsManager user() {
		return new InMemoryUserDetailsManager(
				User.withUsername("dvega").password("{noop}password").authorities("read").build());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.httpBasic().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST,"/userapi/login","/userapi/users").permitAll()
				.antMatchers("/userapi/test").hasRole("USER")
				.anyRequest().authenticated()
				.and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
				.build();
	}

}
