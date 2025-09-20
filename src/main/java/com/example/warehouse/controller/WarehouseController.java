package com.example.warehouse.controller;

import com.example.warehouse.dto.AckDto;
import com.example.warehouse.dto.WarehouseDto;
import com.example.warehouse.dto.WarehouseItemDto;
import com.example.warehouse.dto.WarehouseMapper;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.WarehouseItem;

import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.WarehouseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/warehouses")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WarehouseController {
    WarehouseService warehouseService;
    WarehouseRepository warehouseRepository;
    WarehouseMapper warehouseMapper;

    //    http://localhost:8282/api/warehouses
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody WarehouseDto warehouseDto) {
        Warehouse createWarehouse = warehouseService.createWarehouse(warehouseDto);
        return ResponseEntity.ok(createWarehouse);

    }

    //    http://localhost:8282/api/warehouses/1
    @PutMapping("/{id}")

    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") Long warehouseId,
                                                     @RequestBody WarehouseDto warehouseDto) {
        Warehouse updatedWarehouse = warehouseService.updateWareHouse(warehouseId, warehouseDto);
        return ResponseEntity.ok(updatedWarehouse);
    }

    //    http://localhost:8282/api/warehouses/1
    @DeleteMapping("/{id}")

    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable("id") Long warehouseId) {
        Warehouse deletedwarehouse = warehouseService.deletewarehouse(warehouseId);
        return ResponseEntity.ok(deletedwarehouse);


    }

    //    http://localhost:8282/api/warehouses
    @GetMapping
    public List<WarehouseDto> getAllWarehouses() {
        return warehouseRepository.streamAllBy()
                .map(warehouseMapper::toDto)
                .collect(Collectors.toList());


    }


    //    http://localhost:8282/api/warehouses/1
    @GetMapping("/{id}")
    public WarehouseDto getWarehouseId(@PathVariable("id") Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("warehouse with this id was not found"));
        return warehouseMapper.toDto(warehouse);

    }
//    http://localhost:8282/api/warehouses/1/capacity
    @GetMapping("/{id}/capacity")
    public ResponseEntity<WarehouseDto> getWarehouseCapacity(@PathVariable Long id) {
        WarehouseDto dto = warehouseService.getWarehouseCapacity(id);
        return ResponseEntity.ok(dto);
    }
//    http://localhost:8282/api/warehouses/1/add-product
        @PostMapping("/{id}/add-product")
        public ResponseEntity<WarehouseDto> addProductToWarehouse(
                @PathVariable("id") Long warehouseId,
                @RequestBody WarehouseItemDto request) {

            if (request.getQuantity() <= 0) {
                return ResponseEntity.badRequest().build();
            }

            Product product = warehouseService.getProductById(request.getProductId());

            Warehouse updatedWarehouse = warehouseService.addProductToWarehouse(
                    warehouseId,
                    product,
                    request.getQuantity()
            );
            int totalQuantity = updatedWarehouse.getItems().stream()
                    .mapToInt(WarehouseItem::getQuantity)
                    .sum();

            WarehouseDto dto = new WarehouseDto(
                    updatedWarehouse.getId(),
                    updatedWarehouse.getName(),
                    (double) totalQuantity
            );

//            double freeCapacity = warehouseService.getFreeCapacity(updatedWarehouse);
//
//            WarehouseDto dto = new WarehouseDto(
//                    updatedWarehouse.getId(),
//                    updatedWarehouse.getName(),
//                    freeCapacity
//
//            );

            return ResponseEntity.ok(dto);
        }


    }

