package com.example.warehouse.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseHistoryDto {
    private Long id;
    private String type;
    private LocalDateTime date;
    private List<WarehouseItemDto> items;
}

