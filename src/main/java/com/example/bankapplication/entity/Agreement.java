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
  @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne()
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;

  @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  @Column(name = "interest_rate")
  private Double interestRate;// процентная ставка...

  @Column(name = "status")
  @Enumerated(EnumType.ORDINAL)
  private AgreementStatus status;

  @Column(name = "sum")
  private BigDecimal sum;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Agreement agreement)) return false;
    return id.equals(agreement.id) && account.equals(agreement.account) && product.equals(agreement.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, account, product);
  }
}
