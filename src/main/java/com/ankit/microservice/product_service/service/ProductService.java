package com.ankit.microservice.product_service.service;

import com.ankit.microservice.product_service.dto.ProductRequest;
import com.ankit.microservice.product_service.dto.ProductResponse;
import com.ankit.microservice.product_service.model.Product;
import com.ankit.microservice.product_service.repository.ProductRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Builder
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product  = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("product created sucessfully");
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice()) ;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice())).toList();
    }
}
