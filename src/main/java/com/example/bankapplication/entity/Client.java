package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.ClientStatus;
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
@Table(name = "clients")
public class Client {
  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
  @JoinColumn(name = "manager_id", referencedColumnName = "id")
  private Manager manager;

  @Column(name = "tax_code")
  private String taxCode;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "phone")
  private String phone;

  @Column(name = "status")
  @Enumerated(EnumType.ORDINAL)
  private ClientStatus status;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @OneToMany(mappedBy = "client", fetch = FetchType.LAZY,
          orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
  private List<Account> accounts;

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
