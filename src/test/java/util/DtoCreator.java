package util;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.entity.enums.AccountType;

import java.math.BigDecimal;
import java.util.UUID;

public class DtoCreator {
  public static AccountDto getAccountDto(){
    return new AccountDto(
            UUID.randomUUID().toString(),
            "AccountDtoName",
            AccountType.CREDIT_CARD.toString(),
            AccountStatus.ACTIVE.toString(),
            new BigDecimal("20000").toString());
  }
}
