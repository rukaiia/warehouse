package com.example.warehouse.controller;

import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.dto.ProductMapper;
import com.example.warehouse.dto.WarehouseDto;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;
    ProductRepository productRepository;
    ProductMapper productMapper;

    //    http://localhost:8282/api/products
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product createProduct= productService.createProduct(productDto);
        return ResponseEntity.ok(createProduct);

    }


    //    http://localhost:8282/api/products/1
    @DeleteMapping("/{id}")


    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long productId) {
        Product deletedproduct = productService.deleteProduct(productId);
        return ResponseEntity.ok(deletedproduct);


    }

    //    http://localhost:8282/api/products
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productRepository.streamAllBy()
                .map(productMapper::toDto)
                .collect(Collectors.toList());


    }
}
