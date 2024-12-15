package com.example.medicalcheckup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                .formLogin(httpForm -> {
                    httpForm
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true");
                })
                .logout(logout -> {
                    logout
                        .logoutUrl("/logout")             // URL untuk logout
                        .logoutSuccessUrl("/")            // Redirect setelah logout
                        .invalidateHttpSession(true)      // Invalidate session saat logout
                        .clearAuthentication(true);       // Clear authentication
                })
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/login", "/req/register", "/").permitAll();
                    registry.anyRequest().authenticated();  // Semua request lainnya harus terautentikasi
                })
                .userDetailsService(customUserDetailsService) // Menyediakan custom userDetailsService
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Menggunakan BCryptPasswordEncoder untuk enkripsi password
    }

}
