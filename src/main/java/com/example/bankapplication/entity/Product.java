package com.example.bankapplication.entity;

import com.example.bankapplication.entity.enums.CurrencyCode;
import com.example.bankapplication.entity.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
@Table(name = "products")
public class Product {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "name", nullable = false, length = 70)
  private String name;//Enum от может следать... ипотека, вклад, текущий счет...

  @Column(name = "currency_code", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private CurrencyCode currencyCode;

  @Column(name = "interest_rate", nullable = false)
  private Double interestRate;

  @Column(name = "limit_amount")
  private BigDecimal limitAmount;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private ProductStatus status;

  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
  @JoinColumn(name = "manager_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "FK_PRODUCTS_MANAGERS_MANAGER_ID"))
  private Manager manager;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
          cascade = {MERGE, PERSIST, REFRESH})
  private Set<Agreement> agreements;

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
