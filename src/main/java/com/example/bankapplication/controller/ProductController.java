package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("auth/products")
@Tag(name = "Product Management", description = "Endpoints for product management")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @Operation(summary = "Get product by ID", description = "Get product details by their unique ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = ProductDto.class))),
          @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProductById(
          @Parameter(
                  description = "ID of the product",
                  required = true,
                  example = "6a74faf2-38ce-472f-b8ae-2c4beff14cd1"
          )
          @PathVariable("id") UUID id) {
    ProductDto productDto = productService.findById(id);
    return ResponseEntity.ok(productDto);
  }

  @Operation(summary = "Get product by ID", description = "Get product full details by their unique ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = ProductInfoDto.class))),
          @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/{id}/full")
  public ResponseEntity<ProductInfoDto> getProductInfoById(
          @Parameter(
                  description = "ID of the product",
                  required = true,
                  example = "6a74faf2-38ce-472f-b8ae-2c4beff14cd1"
          )
          @PathVariable("id") UUID id) {
    ProductInfoDto productInfoDto = productService.findInfoById(id);
    return ResponseEntity.ok(productInfoDto);
  }

  @Operation(summary = "Get product list", description = "Get product list details")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = ProductInfoDto.class))),
          @ApiResponse(responseCode = "204", description = "ResourceListEmptyException", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/all")
  public ResponseEntity<List<ProductDto>> getAllProducts() {
    List<ProductDto> products = productService.getAllProducts();

    if (!products.isEmpty()) {
      return ResponseEntity.ok(products);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @Operation(summary = "Get product list", description = "Get product list details with status = ACTIVE")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = ProductInfoDto.class))),
          @ApiResponse(responseCode = "204", description = "ResourceListEmptyException", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/all-active")
  public ResponseEntity<List<ProductDto>> getAllProductsActive() {
    List<ProductDto> products = productService.getAllProductsActive();

    if (!products.isEmpty()) {
      return ResponseEntity.ok(products);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

}
