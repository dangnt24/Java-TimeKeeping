package com.timekeeping.timekeeping.config;

import com.timekeeping.timekeeping.controllers.AuthenticationCookieFilter;
import com.timekeeping.timekeeping.services.CustomAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationCookieFilter authenticationCookieFilter;

    public SecurityConfig(AuthenticationCookieFilter authenticationCookieFilter) {
        this.authenticationCookieFilter = authenticationCookieFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for stateless sessions
                .authorizeHttpRequests(auth -> auth  // Use authorizeHttpRequests instead of authorizeRequests
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")  // Custom login page
                        .failureHandler(new CustomAuthenticationFailureHandler())  // Custom failure handler
                        .permitAll()  // Allow everyone to see the login page
                )
                .logout(logout -> logout.permitAll())  // Allow logout for all users
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session management
                );

        // Add custom filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authenticationCookieFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}