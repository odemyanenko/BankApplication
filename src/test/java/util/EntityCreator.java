package util;

import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.Client;
import com.example.bankapplication.entity.Transaction;
import com.example.bankapplication.entity.enums.*;

import java.math.BigDecimal;
import java.util.UUID;

import static util.TimeUtil.getCurrentDateTime;

public class EntityCreator {
  public static Account getAccount(UUID id) {
    Account account = new Account();
    account.setId(id);
    account.setName("DE02500105170137075030");
    account.setType(AccountType.DEBIT_CARD);
    account.setStatus(AccountStatus.ACTIVE);
    account.setBalance(BigDecimal.valueOf(20000.0));

    return account;
  }

  public static Account getAccountTo(UUID id) {
    Account account = new Account();
    account.setId(id);
    account.setName("DE02120300000000202051");
    account.setType(AccountType.DEBIT_CARD);
    account.setStatus(AccountStatus.ACTIVE);
    account.setBalance(BigDecimal.valueOf(5000.0));

    return account;
  }

  public static Client getClient(UUID id) {
    Client client = new Client();
    client.setId(id);
    client.setFirstName("Sofia");
    client.setLastName("Test");
    client.setTaxCode("1234567890");
    client.setEmail("sofia.herrenko@gmail.com");
    client.setPhone("+493028387765");
    client.setCreatedAt(getCurrentDateTime());
    client.setUpdatedAt(getCurrentDateTime());
    client.setStatus(ClientStatus.ACTIVE);

    return client;
  }

  public static Transaction getTransaction(UUID id) {
    Transaction transaction = new Transaction();
    transaction.setId(id);
    transaction.setType(TransactionType.CASH);
    transaction.setAmount(BigDecimal.valueOf(10000.0));
    transaction.setDescription("Test transaction");
    transaction.setStatus(TransactionStatus.APPROVED);
    transaction.setCreatedAt(getCurrentDateTime());

    transaction.setDebitAccount(getAccount(UUID.randomUUID()));
    transaction.setCreditAccount(getAccountTo(UUID.randomUUID()));

    return transaction;
  }

}
