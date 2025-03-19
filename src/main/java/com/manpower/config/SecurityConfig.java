package com.manpower.config;

import com.manpower.filter.JWTTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JWTTokenFilter jwtTokenFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            // Enable CORS and disable CSRF
            .cors(cors -> cors.configurationSource(request -> {
              CorsConfiguration config = new CorsConfiguration();
              config.setAllowedOrigins(List.of("http://localhost:4200")); // Allow frontend origin
              config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
              config.setAllowedHeaders(List.of("*"));
              config.setAllowCredentials(true);
              return config;
            }))
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(AbstractHttpConfigurer::disable)
            // Setup authorization
            .authorizeHttpRequests(auth -> {
              auth.requestMatchers("/public/**").permitAll()
                      .requestMatchers("/api/login").permitAll()
                      .requestMatchers("/swagger-ui/*").permitAll()
                      .requestMatchers("/v3/*").permitAll()
                      .requestMatchers("/v3/api-docs/swagger-config").permitAll()
                      .anyRequest().authenticated();
            })
            .exceptionHandling(exceptionHandling -> exceptionHandling
                    .accessDeniedPage("/error") // Redirect to /error page on access denied
            )
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }

}