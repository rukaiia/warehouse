package com.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseItemDto {
    private Long productId;
    private String productName;
    private int quantity;
}

