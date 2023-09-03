package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.entity.enums.AccountType;
import com.example.bankapplication.entity.enums.CurrencyCode;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
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
  @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  @Enumerated(EnumType.ORDINAL)
  private AccountType type;

  @Column(name = "status")
  @Enumerated(EnumType.ORDINAL)
  private AccountStatus status;

  @Column(name = "balance")
  private BigDecimal balance;

  @Column(name = "currency_code")
  @Enumerated(EnumType.STRING)
  private CurrencyCode currencyCode;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

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

