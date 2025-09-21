package com.example.warehouse.dto;

import com.example.warehouse.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .volumePerUnit(product.getVolumePerUnit())

                .build();
    }
    
}

