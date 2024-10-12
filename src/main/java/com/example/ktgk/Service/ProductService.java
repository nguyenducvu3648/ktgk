package com.example.ktgk.Service;

import com.example.ktgk.Dto.Request.ProductCreationRequest;
import com.example.ktgk.Dto.Request.ProductUpdateRequest;
import com.example.ktgk.Dto.Response.ApiResponse;
import com.example.ktgk.Dto.Response.ProductResponse;
import com.example.ktgk.Enum.ErrorCode;
import com.example.ktgk.Exception.AppException;
import com.example.ktgk.Mapper.ProductMapper;
import com.example.ktgk.Model.Product;
import com.example.ktgk.Repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    public ApiResponse<List<ProductResponse>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse>productResponses = products.stream()
                .map(productMapper :: toProductResponse)
                .collect(Collectors.toList());
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productResponses)
                .build();
    }
    public ProductResponse createProduct(ProductCreationRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }
        Product product = productMapper.toProduct(request);
        product = productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    public ApiResponse<ProductResponse> getProductById(String id) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED)); ;
    ProductResponse productResponse = productMapper.toProductResponse(product);
    return ApiResponse.<ProductResponse>builder()
            .data(productResponse)
            .build();
    }

    public ProductResponse updateProduct(String id , ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productMapper.updateProduct(product, request);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
