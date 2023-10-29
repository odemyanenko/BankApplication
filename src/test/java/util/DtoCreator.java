package util;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.entity.enums.AccountType;
import com.example.bankapplication.entity.enums.AgreementStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

  public static AgreementDto getAgreementDto(){
    return new AgreementDto(
            UUID.randomUUID().toString(),
            Double.toString(4.2),
            AgreementStatus.ACTIVE.toString(),
            new BigDecimal("20000").toString(),
            new Timestamp(System.currentTimeMillis()).toString());
  }

  public static ClientDto getClientDto(){
    return new ClientDto(
            UUID.randomUUID().toString(),
            "Anna",
            "Ivanenko",
            "annaivanenko@gmail.com",
            "Ukraine",
            "+493026304819",
            new Timestamp(System.currentTimeMillis()).toString()
            );
  }
}
