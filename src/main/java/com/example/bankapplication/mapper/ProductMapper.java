package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductDto.class})
public interface ProductMapper {
  ProductDto toDto(Product product);

  @Mapping(source = "manager.id", target = "managerId")
  @Mapping(source = "manager.lastName", target = "managerLastName")
  @Mapping(source = "manager.firstName", target = "managerFirstName")
  ProductInfoDto toInfoDto(Product product);

//  Product toEntity(ProductDto productDto);
//
//  List<ProductDto> toDtoList(List<Product> managers);
}
