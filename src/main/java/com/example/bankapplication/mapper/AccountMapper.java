package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.entity.enums.AccountType;
import com.example.bankapplication.entity.enums.CurrencyCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  AccountDto toDto(Account account);

  @Mapping(target = "type", expression = "java(stringToAccountType(accountDto.getType()))")
  @Mapping(target = "status", expression = "java(stringToAccountStatus(accountDto.getStatus()))")
  @Mapping(target = "balance", expression = "java(stringToBigDecimal(accountDto.getBalance()))")
  @Mapping(target = "currencyCode", expression = "java(stringToCurrencyCode(accountDto.getCurrencyCode()))")
  @Mapping(target = "createdAt", expression = "java(getCurrentTime())")
  @Mapping(target = "updatedAt", expression = "java(getCurrentTime())")
  Account toAccountEntity(AccountDto accountDto);

  default Timestamp getCurrentTime() {
    return new Timestamp(System.currentTimeMillis());
  }

  default AccountType stringToAccountType(String s){
    return AccountType.valueOf(s.toUpperCase());
  }

  default AccountStatus stringToAccountStatus(String s){
    return AccountStatus.valueOf(s.toUpperCase());
  }

  default CurrencyCode stringToCurrencyCode(String s){
    return CurrencyCode.valueOf(s.toUpperCase());
  }

  default BigDecimal stringToBigDecimal(String s) {
    return new BigDecimal(s);
  }

  List<AccountDto> toDtoList(List<Account> accounts);
}
