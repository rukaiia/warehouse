package com.example.warehouse.service;

import com.example.warehouse.dto.ShipmentDto;
import com.example.warehouse.dto.ShipmentItemDto;
import com.example.warehouse.entity.ShipmentItem;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.entity.WarehouseItem;
import com.example.warehouse.entity.Shipment;
import com.example.warehouse.repository.ShipmentRepository;


import com.example.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentService {



        private final WarehouseService warehouseService;
        private final ShipmentRepository packageRepository;
        private final WarehouseRepository warehouseRepository;

    public ShipmentDto createShipment(ShipmentDto request) {
        Warehouse warehouse = warehouseService.getWarehouseById(request.getWarehouseId());

        Shipment pkg = new Shipment();
        pkg.setWarehouse(warehouse);
        pkg.setDeliveryAddress(request.getDeliveryAddress());

        List<ShipmentItem> items = request.getItems().stream().map(itemDto -> {
            WarehouseItem warehouseItem = findWarehouseItem(warehouse, itemDto.getProductId());

            if (warehouseItem.getQuantity() < itemDto.getQuantity()) {
                throw new IllegalArgumentException("Недостаточно товара на складе!");
            }

            warehouseItem.setQuantity(warehouseItem.getQuantity() - itemDto.getQuantity());

            ShipmentItem pi = new ShipmentItem();
            pi.setProduct(warehouseItem.getProduct());
            pi.setQuantity(itemDto.getQuantity());
            pi.setShipment(pkg);
            return pi;
        }).toList();

        pkg.setItems(items);

        warehouseRepository.save(warehouse);

        Shipment saved = packageRepository.saveAndFlush(pkg);

        return ShipmentDto.builder()
                .id(saved.getId())
                .warehouseId(saved.getWarehouse().getId())
                .deliveryAddress(saved.getDeliveryAddress())
                .items(saved.getItems().stream()
                        .map(i -> new ShipmentItemDto(
                                i.getId(),
                                i.getShipment().getId(),
                                i.getProduct().getId(),
                                i.getQuantity()
                        ))
                        .toList())
                .build();
    }
    private WarehouseItem findWarehouseItem(Warehouse warehouse, Long productId) {

        return warehouse.getItems().stream()
                .filter(wi -> wi.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Товар с id " + productId + " не найден на складе " + warehouse.getName())
                );
    }

    }


