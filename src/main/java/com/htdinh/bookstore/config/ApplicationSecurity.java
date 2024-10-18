package com.htdinh.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/v3/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
    };
    private final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/auth/login",
            "/api/v1/books/**",
            "/api/v1/user/**",
            "/swagger-ui/index.html",
            "/api/v1/cart",
            "/api/v1/cart-item/**",
            "/api/v1/orders/**"
    };
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeRequests()
//                .antMatchers("/api/v1/profile/user").hasAnyAuthority("USER")
//                .antMatchers("/api/v1/profile/admin").hasAnyAuthority("ADMIN")
//                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
//                .antMatchers(AUTH_WHITELIST).permitAll()
//                .anyRequest().authenticated();
//
//        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests().antMatchers("/**").permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(
//                username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"))
//        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
