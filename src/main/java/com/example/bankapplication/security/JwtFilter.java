package com.example.bankapplication.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.StringUtils.hasText;

@Component
public class JwtFilter extends GenericFilterBean {
  private final CustomUserDetailsService userDetailsService;
  private final JwtProvider jwtprovider;

  public JwtFilter(CustomUserDetailsService userDetailsService, JwtProvider jwtprovider) {
    this.userDetailsService = userDetailsService;
    this.jwtprovider = jwtprovider;
  }


  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {
    String token = getTokenFromRequest((HttpServletRequest) servletRequest);
    if (token != null && jwtprovider.validateToken(token)) {
      setCustomUserDetailsToSecurityContextHolder(token);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  public void setCustomUserDetailsToSecurityContextHolder(String token) {
    String email = jwtprovider.getEmailFromToken(token);
    CustomUserDetails customUserDetails = userDetailsService.loadUserByUsername(email);
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails,
            null, customUserDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearer = request.getHeader(AUTHORIZATION);
    if (hasText(bearer) && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }

}
