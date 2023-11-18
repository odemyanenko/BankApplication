package util;

import com.example.bankapplication.entity.*;
import com.example.bankapplication.entity.enums.*;

import java.math.BigDecimal;
import java.util.UUID;

import static util.TimeUtil.getCurrentDateTime;

public class EntityCreator {
  public static Account getAccount(UUID id) {
    if (id == null) {
      return null;
    }

    Account account = new Account();
    account.setId(id);
    account.setName("DE02500105170137075030");
    account.setType(AccountType.DEBIT_CARD);
    account.setStatus(AccountStatus.ACTIVE);
    account.setBalance(BigDecimal.valueOf(20000.0));

    return account;
  }

  public static Account getAccountTo(UUID id) {
    if (id == null) {
      return null;
    }

    Account account = new Account();
    account.setId(id);
    account.setName("DE02120300000000202051");
    account.setType(AccountType.DEBIT_CARD);
    account.setStatus(AccountStatus.ACTIVE);
    account.setBalance(BigDecimal.valueOf(5000.0));

    return account;
  }

  public static Client getClient(UUID id) {
    if (id == null) {
      return null;
    }

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
    if (id == null) {
      return null;
    }

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

  public static Manager getManager(UUID id){
    if (id == null) {
      return null;
    }

    Manager manager = new Manager();
    manager.setId(id);
    manager.setFirstName("Ivan");
    manager.setLastName("Petrov");
    manager.setStatus(ManagerStatus.ACTIVE);
    manager.setCreatedAt(TimeUtil.getCurrentDateTime());
    manager.setUpdatedAt(TimeUtil.getCurrentDateTime());

    return manager;
  }

  public static Product getProduct(UUID id, UUID manager_id){
    if (id == null) {
      return null;
    }

    Product product = new Product();
    product.setId(id);
    product.setName("Mortgage");
    product.setCurrencyCode(CurrencyCode.EUR);
    product.setInterestRate(7.3);
    product.setLimitAmount(BigDecimal.valueOf(13000.0));
    product.setStatus(ProductStatus.ACTIVE);
    product.setCreatedAt(TimeUtil.getCurrentDateTime());
    product.setUpdatedAt(TimeUtil.getCurrentDateTime());

    product.setManager(getManager(manager_id));

    return product;
  }

  public static Agreement getAgreement(UUID id){
    if (id == null) {
      return null;
    }

    Agreement agreement = new Agreement();
    agreement.setId(id);
    agreement.setInterestRate(7.3);
    agreement.setStatus(AgreementStatus.ACTIVE);
    agreement.setSum(BigDecimal.valueOf(12300.0));
    agreement.setCreatedAt(TimeUtil.getCurrentDateTime());
    agreement.setUpdatedAt(TimeUtil.getCurrentDateTime());
    agreement.setAccount(getAccount(UUID.randomUUID()));
    agreement.setProduct(getProduct(UUID.randomUUID(), UUID.randomUUID()));

    return agreement;
  }

}
