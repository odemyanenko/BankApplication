package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.TransactionStatus;
import com.example.bankapplication.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne()
  @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
  private Account debitAccount;// получатель

  @ManyToOne()
  @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
  private Account creditAccountId;// отправитель

  @Column(name = "type")
  @Enumerated(EnumType.ORDINAL)
  private TransactionType type;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "description")
  private String description;

  @Column(name = "status")
  @Enumerated(EnumType.ORDINAL)
  private TransactionStatus status;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Transaction that)) return false;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
