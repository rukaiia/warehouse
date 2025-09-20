package com.example.warehouse.service;

import com.example.warehouse.dto.WarehouseDto;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;


    public Warehouse createWarehouse(WarehouseDto warehouseDto){
        Warehouse warehouse = Warehouse.builder()
                .capacity(warehouseDto.getCapacity())
                .name(warehouseDto.getName())
                .build();

        Warehouse saved = warehouseRepository.saveAndFlush(warehouse);
        log.info("Warehouse created: {}", saved.getName());

        return saved;




    }
    @Transactional
    public Warehouse deletewarehouse(Long warehouseId){

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow( () -> new RuntimeException("Warehouse with this Id wasn't found:"));
       warehouseRepository.delete(warehouse);

        return warehouse;
    }


    @Transactional
    public Warehouse updateWareHouse(Long warehouseId, WarehouseDto warehouseDto){
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Задача с таким id не найдена"));

        if (warehouseDto.getCapacity() != null){
            warehouse.setCapacity(warehouseDto.getCapacity());
        }
        if (warehouseDto.getName() != null){
            warehouse.setName(warehouseDto.getName());
        }
        return warehouseRepository.saveAndFlush(warehouse);


    }














}
