package com.example.bankapplication.security;

import com.example.bankapplication.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class UserProvider {
  public User getCurrentUser() {
    return ((CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal())
            .getUser();
  }
}
