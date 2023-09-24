package com.example.bankapplication.repository;

import com.example.bankapplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

  Optional<Account> getAccountById(UUID id);

//  List<Account> getAllByActive(Boolean active);
}