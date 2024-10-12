package com.example.ktgk.Mapper;

import com.example.ktgk.Dto.Request.ProductCreationRequest;
import com.example.ktgk.Dto.Request.ProductUpdateRequest;
import com.example.ktgk.Dto.Response.ProductResponse;
import com.example.ktgk.Model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductCreationRequest request);

    ProductResponse toProductResponse(Product product);

    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}