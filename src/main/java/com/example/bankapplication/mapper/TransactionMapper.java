package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
  TransactionDto toDto(Transaction transaction);
}
