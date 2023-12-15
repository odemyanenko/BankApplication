package com.example.bankapplication.security;

import com.example.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
  private final UserRepository userRepository;
  @Transactional
  @Override
  public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
            .map(CustomUserDetails::new)
            .orElseThrow(() -> new RuntimeException("UserNotFound with email " + email));
  }
}
