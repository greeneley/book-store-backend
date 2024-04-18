package com.htdinh.bookstore.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }
    
//	@Autowired
//	private JwtAuthFilter authFilter;
//
//	// User Creation 
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new UserInfoService();
//	}
//
//	// Configuring HttpSecurity 
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.headers().frameOptions().disable();
//		return http.csrf().disable()
//				.authorizeHttpRequests()
//				.requestMatchers(new AntPathRequestMatcher("/auth/welcome"), new AntPathRequestMatcher("/auth/addNewUser"),new AntPathRequestMatcher("/auth/generateToken")).permitAll()
//				.and()
//				.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/auth/user/**")).authenticated()
//				.and()
//				.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/auth/admin/**")).authenticated()
//				.and()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.authenticationProvider(authenticationProvider())
//				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//				.build();
//	}
//
//	// Password Encoding 
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService());
//		authenticationProvider.setPasswordEncoder(passwordEncoder());
//		return authenticationProvider;
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
} 