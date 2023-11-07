package util;

import com.example.bankapplication.dto.*;
import com.example.bankapplication.entity.enums.*;

import java.math.BigDecimal;
import java.util.UUID;

import static util.TimeUtil.getCurrentDateTime;

public class DtoCreator {
  public static AccountDto getAccountDto(UUID id) {
    return new AccountDto(
            id.toString(),
            "DE02500105170137075030",
            AccountType.DEBIT_CARD.toString(),
            AccountStatus.ACTIVE.toString(),
            BigDecimal.valueOf(20000.0).toString());
  }

  public static AccountDto getAccountToDto(UUID id) {
    return new AccountDto(
            id.toString(),
            "DE02120300000000202051",
            AccountType.DEBIT_CARD.toString(),
            AccountStatus.ACTIVE.toString(),
            BigDecimal.valueOf(5000.0).toString());
  }

  public static AgreementDto getAgreementDto() {
    return new AgreementDto(
            UUID.randomUUID().toString(),
            Double.toString(4.2),
            AgreementStatus.ACTIVE.toString(),
            BigDecimal.valueOf(20000.0).toString(),
            getCurrentDateTime().toString()
    );
  }

  public static ClientDto getClientDto(UUID id) {
    return new ClientDto(
            id.toString(),
            "Sofia",
            "Test",
            "1234567890",
            "sofia.herrenko@gmail.com",
            "Ukraine",
            "+493028387765",
            getCurrentDateTime().toString()
    );
  }

  public static TransactionDto getTransactionDto(UUID id){
    return new TransactionDto(
            id.toString(),
            TransactionType.CASH.toString(),
            BigDecimal.valueOf(10000.0).toString(),
            "Test transaction",
            TransactionStatus.APPROVED.toString(),
            "DE02500105170137075030",
            "DE02120300000000202051"
    );
  }

  public static ProductDto getProductDto(UUID id){
    return new ProductDto(
            id.toString(),
            "Mortgage",
            CurrencyCode.EUR.toString(),
            Double.valueOf(7.3).toString(),
            BigDecimal.valueOf(13000.0).toString(),
            ProductStatus.ACTIVE.toString()
    );
  }

}
