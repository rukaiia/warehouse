package com.example.warehouse.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {
    private Long id;

    private Long warehouseId;
    private List<DeliveryItemDto> items;
}
