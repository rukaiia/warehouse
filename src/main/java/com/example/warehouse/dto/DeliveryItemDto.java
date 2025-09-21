package com.example.warehouse.dto;

import com.example.warehouse.entity.Delivery;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryItemDto {

    private Long id;

    private Long deliveryId;

    private Long productId;

    private int quantity;


}
