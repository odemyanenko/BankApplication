package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TransactionDto.class})
public interface TransactionMapper {
  @Mapping(source = "debitAccount.name", target = "debitAccountName")
  @Mapping(source = "creditAccount.name", target = "creditAccountName")
  TransactionDto toDto(Transaction transaction);
}
