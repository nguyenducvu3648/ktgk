package com.example.ktgk.Controller;

import com.example.ktgk.Dto.Request.ProductCreationRequest;
import com.example.ktgk.Dto.Request.ProductUpdateRequest;
import com.example.ktgk.Dto.Response.ApiResponse;
import com.example.ktgk.Dto.Response.ProductResponse;
import com.example.ktgk.Service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/product")
@Api(value = "User Management System", description = "Operations pertaining to user management")
public class ProductController {

    ProductService productService ;
    @ApiOperation(value = "View a list of available users", response = List.class)
    @GetMapping("/getAll")
    public ApiResponse<List<ProductResponse>> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }

    @PostMapping("/createProduct")
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request){
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(productService.createProduct(request));
        return apiResponse;
    }

    @PutMapping("/updateProduct/{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductUpdateRequest request){
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(productService.updateProduct(id, request));
        return apiResponse;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable String id){
        productService.deleteProduct(String.valueOf(UUID.fromString(id)));
        return "product has been deleted";
    }
}
