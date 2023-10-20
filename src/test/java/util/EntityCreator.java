package util;

import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.entity.enums.AccountType;

import java.math.BigDecimal;
import java.util.UUID;

public class EntityCreator {
  public static Account getAccount() {
    Account account = new Account();
    account.setId(UUID.randomUUID());
    account.setName("AccountName");
    account.setType(AccountType.DEBIT_CARD);
    account.setStatus(AccountStatus.ACTIVE);
    account.setBalance(new BigDecimal(20000.0));
    return account;
  }
}
