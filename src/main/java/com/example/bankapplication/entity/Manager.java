package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.ManagerStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "managers")
public class Manager {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "last_name", nullable = false, length = 50)
  private String lastName;

  @Column(name = "first_name", length = 50)
  private String firstName;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private ManagerStatus status;

  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at", nullable = false)
  private Timestamp updatedAt;

  @JsonIgnore
  @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY,
          cascade = {MERGE, PERSIST, REFRESH})
  private Set<Client> clients;

  @JsonIgnore
  @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY,
          cascade = {MERGE, PERSIST, REFRESH})
  private Set<Product> products;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Manager manager)) return false;
    return firstName.equals(manager.firstName) && lastName.equals(manager.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}
