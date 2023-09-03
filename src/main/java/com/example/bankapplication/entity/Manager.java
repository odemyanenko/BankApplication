package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.ManagerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
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
  @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "status")
  @Enumerated(EnumType.ORDINAL)
  private ManagerStatus status;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY,
          orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
  private List<Client> clients;

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
