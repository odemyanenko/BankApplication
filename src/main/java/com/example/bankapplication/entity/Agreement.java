package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.AgreementStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "agreements")

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "interest_rate")
  private Double interestRate;// процентная ставка...

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private AgreementStatus status;

  @Column(name = "total")
  private BigDecimal sum;

  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;

  @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Agreement agreement)) return false;
    return Objects.equals(id, agreement.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
