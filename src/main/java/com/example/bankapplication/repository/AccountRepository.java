package com.example.bankapplication.repository;

import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
  Optional<Account> findById(UUID id);

  List<Account> getAllByStatus(AccountStatus status);

  @Query(value = "SELECT get_balance_account(:id)", nativeQuery = true)
  BigDecimal getAccountBalance(@Param("id") UUID accountId);
}
