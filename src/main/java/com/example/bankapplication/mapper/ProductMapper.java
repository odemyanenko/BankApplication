package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.entity.Product;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Mapper(componentModel = "spring", uses = {ProductDto.class})
public interface ProductMapper {
  ProductDto toDto(Product product);

  @Mapping(target = "createdAt", expression = "java(getFormatUTCData(product.getCreatedAt()))")
  @Mapping(target = "updatedAt", expression = "java(getFormatUTCData(product.getUpdatedAt()))")
  @Mapping(source = "manager.id", target = "managerId")
  @Mapping(source = "manager.lastName", target = "managerLastName")
  @Mapping(source = "manager.firstName", target = "managerFirstName")
  ProductInfoDto toInfoDto(Product product);

  default String getFormatUTCData(Timestamp datetime) {
    DateFormat dfActual = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    dfActual.setTimeZone(TimeZone.getTimeZone("UTC"));
    return dfActual.format(datetime);
  }

  List<ProductDto> toDtoList(List<Product> products);
}
