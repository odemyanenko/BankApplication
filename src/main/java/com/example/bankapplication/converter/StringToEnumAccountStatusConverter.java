package com.example.bankapplication.converter;

import com.example.bankapplication.entity.enums.AccountStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumAccountStatusConverter implements Converter<String, AccountStatus> {

  @Override
  public AccountStatus convert(String source) {
      return AccountStatus.valueOf(source.toUpperCase());
  }
}


