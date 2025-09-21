package com.example.warehouse.dto;

import com.example.warehouse.entity.Warehouse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class WarehouseMapper {

    public WarehouseDto toDto(Warehouse warehouse) {
        List<WarehouseItemDto> items = warehouse.getItems().stream()
                .map(item -> WarehouseItemDto.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .build()
                )
                .toList();

        return WarehouseDto.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .capacity(warehouse.getCapacity())
                .items(items)
                .build();
    }
}



