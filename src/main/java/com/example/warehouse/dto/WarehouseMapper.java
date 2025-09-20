package com.example.warehouse.dto;

import com.example.warehouse.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    public WarehouseDto toDto(Warehouse warehouse) {
        return WarehouseDto.builder()
                .id(warehouse.getId())
                .capacity(warehouse.getCapacity())
                .name(warehouse.getName())

                .build();
    }
    
}

