package com.example.demo.global.filter;

import java.io.IOException;
import java.rmi.RemoteException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtAuthProvider;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authorizationHeader = request.getHeader("Authorization");

    System.out.println("1");

    if (authorizationHeader != null) {
      String token = authorizationHeader;
      System.out.println("2");
      if (jwtAuthProvider.isTokenValid(token)) {
        Long userId = jwtAuthProvider.getMemberIdFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId.toString());
        System.out.println("3");
        if (userDetails != null) {
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, "", userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          System.out.println("4");
        } else {
          System.out.println("5");
          throw new RuntimeException(GlobalErrorCode.NOT_FOUND_MEMBER.getMessage());
        }
      } else {
        System.out.println("6");
        throw new RemoteException(GlobalErrorCode.INVALID_TOKEN.getMessage());
      }
    }
    filterChain.doFilter(request, response);
  }
}
