package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "email", nullable = false, length = 60)
  private String email;

  @Column(name = "password", nullable = false, length = 60)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private UserRole role;
}
