package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.entity.enums.AccountType;
import com.example.bankapplication.entity.enums.CurrencyCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
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
@Table(name = "accounts")
public class Account {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "type", nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountType type;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountStatus status;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

  @Column(name = "currency_code", nullable = false)
  @Enumerated(EnumType.STRING)
  private CurrencyCode currencyCode;

  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;

  @JsonIgnore
  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY,
          cascade = {MERGE, PERSIST, REFRESH})
  private Set<Agreement> agreements = new HashSet<>();;

  @JsonIgnore
  @OneToMany(mappedBy = "debitAccount", fetch = FetchType.LAZY,
          cascade = {MERGE, PERSIST, REFRESH})
  private Set<Transaction> debitTransactions = new HashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "creditAccount", fetch = FetchType.LAZY,
          cascade = {MERGE, PERSIST, REFRESH})
  private Set<Transaction> creditTransactions = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account account)) return false;
    return name.equals(account.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}

