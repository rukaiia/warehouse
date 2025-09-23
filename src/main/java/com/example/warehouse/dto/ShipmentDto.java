package com.example.warehouse.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDto {
    private Long id;

    private Long warehouseId;
    private String deliveryAddress;

    private List<ShipmentItemDto> items;
}
