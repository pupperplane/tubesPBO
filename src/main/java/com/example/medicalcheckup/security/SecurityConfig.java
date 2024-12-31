package com.example.medicalcheckup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.medicalcheckup.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailsService customUserDetailsService;

    public SecurityConfig(MyUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.ignoringRequestMatchers("/admin/mcu/update/**", "/admin/mcu/delete/**", "admin/reports/**"))
                .formLogin(httpForm -> {
                    httpForm
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler())
                        .failureUrl("/login?error=true");
                })
                .logout(logout -> {
                    logout
                        .logoutUrl("/logout")             
                        .logoutSuccessUrl("/")           
                        .invalidateHttpSession(true)      
                        .clearAuthentication(true);       
                })
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/login", "/req/register", "/").permitAll();
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
                    registry.anyRequest().authenticated(); 
                })
                .userDetailsService(customUserDetailsService) // Menyediakan custom userDetailsService
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

}
