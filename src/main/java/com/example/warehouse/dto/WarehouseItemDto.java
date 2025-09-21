package com.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WarehouseItemDto {
    private Long productId;
    private String productName;
    private int quantity;
}

