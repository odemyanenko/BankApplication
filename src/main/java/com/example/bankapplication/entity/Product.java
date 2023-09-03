package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.CurrencyCode;
import com.example.bankapplication.entity.enums.ProductStatus;
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
@Table(name = "products")
public class Product {
  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
  @JoinColumn(name = "manager_id", referencedColumnName = "id")
  private Manager manager;

  @Column(name = "name")
  private String name;//Enum от может следать... ипотека, вклад, текущий счет...

  @Column(name = "currency_code")
  @Enumerated(EnumType.ORDINAL)
  private CurrencyCode currencyCode;

  @Column(name = "interest_rate")
  private Double interestRate;

  @Column(name = "limit")
  private BigDecimal limit;

  @Column(name = "status")
  @Enumerated(EnumType.ORDINAL)
  private ProductStatus status;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Product product)) return false;
    return Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
