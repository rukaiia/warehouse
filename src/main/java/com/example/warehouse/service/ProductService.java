package com.example.warehouse.service;

import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.dto.WarehouseDto;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public Product createProduct(ProductDto productDto){
        Product product = Product.builder()
                .name(productDto.getName())
                .volumePerUnit(productDto.getVolumePerUnit())
                .build();

        Product saved = productRepository.saveAndFlush(product);
        log.info("Product created: {}", saved.getName());

        return saved;




    }
}
