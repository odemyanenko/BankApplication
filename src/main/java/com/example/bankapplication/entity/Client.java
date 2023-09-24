package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.ClientStatus;
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
@Table(name = "clients")
public class Client {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "last_name", nullable = false, length = 50)
  private String lastName;

  @Column(name = "first_name", length = 50)
  private String firstName;

  @Column(name = "tax_code", nullable = false, length = 20)
  private String taxCode;

  @Column(name = "email", nullable = false, length = 60)
  private String email;

  @Column(name = "address", length = 80)
  private String address;

  @Column(name = "phone", nullable = false, length = 20)
  private String phone;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private ClientStatus status;

  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
  @JoinColumn(name = "manager_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "FK_CLIENTS_MANAGERS_MANAGER_ID"))
  private Manager manager;

  @OneToMany(mappedBy = "client", fetch = FetchType.LAZY,
          orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
  private Set<Account> accounts;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Client client)) return false;
    return id.equals(client.id) && taxCode.equals(client.taxCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, taxCode);
  }
}
