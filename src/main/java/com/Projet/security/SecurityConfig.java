package com.Projet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private  AppUserDetailsService myUserDetailsService;
	
	
	
	//@Bean
    //SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	//	http.csrf().disable()
    //    .authorizeRequests().requestMatchers(
	//			 "/register**",
	//                "/js/**",
	//                "/css/**",
	//                "/img/**").permitAll()
    //        .anyRequest().authenticated()
    //        .and()
    //    .userDetailsService(myUserDetailsService)
    //    .headers().frameOptions().sameOrigin()
    //    .and()
    //    .formLogin()
    //        .permitAll()
    //    .and()
    //    .logout()
    //        .invalidateHttpSession(true)
    //        .clearAuthentication(true)
    //        .logoutSuccessUrl("/login?logout")
    //        .permitAll();

    //return http.build();
    //}
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        		.csrf().disable()
                .authorizeRequests(auth -> auth
                 
                        .anyRequest().authenticated()
                        
                )
                .userDetailsService(myUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .httpBasic(Customizer.withDefaults())
                
                .build();
    }
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	//@Bean
    //public BCryptPasswordEncoder passwordEncoder() {
    //   return new BCryptPasswordEncoder();
    //}

}









