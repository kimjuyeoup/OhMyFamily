package com.example.demo.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.global.filter.JwtAuthExceptionHandlingFilter;
import com.example.demo.global.filter.JwtRequestFilter;
import com.example.demo.global.handler.JwtAccessDeniedHandler;
import com.example.demo.global.handler.JwtAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtRequestFilter jwtRequestFilter;
  private final JwtAuthExceptionHandlingFilter jwtAuthExceptionHandlingFilter;

  private final String[] allowedUrls = {
    "/h2-console/**", "/api/v1/member/kakao", "/api/v1/member/reissue", "/kakao/callback", "/health"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.formLogin(AbstractHttpConfigurer::disable);

    http.httpBasic(AbstractHttpConfigurer::disable);

    http.csrf(AbstractHttpConfigurer::disable);

    http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

    http.sessionManagement(
        sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.exceptionHandling(
        (configurer ->
            configurer
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)));

    http.authorizeHttpRequests(
        (authorize) ->
            authorize
                .requestMatchers("/api/user")
                .permitAll()
                .requestMatchers(allowedUrls)
                .permitAll()
      
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtAuthExceptionHandlingFilter, JwtRequestFilter.class);

    return http.build();
  }
}
