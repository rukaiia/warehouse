package com.example.warehouse.service;

import com.example.warehouse.dto.WareHouseFreeDto;
import com.example.warehouse.dto.WarehouseDto;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.entity.WarehouseItem;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;


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
    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse with id " + id + " not found"));
    }

    public double getUsedCapacity(Warehouse warehouse) {
        if (warehouse.getItems() == null || warehouse.getItems().isEmpty()) {
            return 0.0;
        }
        return warehouse.getItems().stream()
                .mapToDouble(item -> {
                    if (item.getProduct() == null) return 0.0;
                    return item.getQuantity() * item.getProduct().getVolumePerUnit();
                })
                .sum();
    }

    public double getFreeCapacity(Warehouse warehouse) {
        double used = getUsedCapacity(warehouse);
        return Math.max(0, warehouse.getCapacity() - used);
    }

    public WareHouseFreeDto getWarehouseCapacity(Long warehouseId) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        double used = getUsedCapacity(warehouse);
        double free = getFreeCapacity(warehouse);


        return new WareHouseFreeDto(
                warehouse.getId(),
                warehouse.getName(),

                free
        );
    }
    public boolean canAddProduct(Warehouse warehouse, Product product, int quantity) {
        double requiredVolume = product.getVolumePerUnit() * quantity;
        return getFreeCapacity(warehouse) >= requiredVolume;
    }



    @Transactional
    public Warehouse addProductToWarehouse(Long warehouseId, Product product, int quantity) {
        Warehouse warehouse = getWarehouseById(warehouseId);

        if (!canAddProduct(warehouse, product, quantity)) {
            throw new RuntimeException("Not enough free capacity to add this product");
        }

        Optional<WarehouseItem> existingItem = warehouse.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            WarehouseItem newItem = WarehouseItem.builder()
                    .warehouse(warehouse)
                    .product(product)
                    .quantity(quantity)
                    .build();

            warehouse.getItems().add(newItem);
        }

        return warehouseRepository.saveAndFlush(warehouse);
    }


    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(
                        "Product with id " + productId + " not found"));
    }
}
