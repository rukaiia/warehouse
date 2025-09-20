package com.example.warehouse.dto;

import com.example.warehouse.entity.WarehouseItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {
    private Long id;

    private String name;

    private Double capacity;


}
