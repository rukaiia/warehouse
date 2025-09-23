package com.example.warehouse.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentItemDto {
    private Long id;

    private Long pkgId;

    private Long productId;

    private int quantity;



}
