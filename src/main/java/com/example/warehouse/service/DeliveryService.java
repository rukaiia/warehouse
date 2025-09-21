package com.example.warehouse.service;

import com.example.warehouse.dto.DeliveryDto;
import com.example.warehouse.dto.DeliveryItemDto;
import com.example.warehouse.entity.Delivery;
import com.example.warehouse.entity.DeliveryItem;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.repository.DeliveryRepository;
import com.example.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {
   private final WarehouseService warehouseService;
    private final  ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;






        public DeliveryDto createDelivery(DeliveryDto request) {
            Warehouse warehouse = warehouseService.getWarehouseById(request.getWarehouseId());

            Delivery delivery = new Delivery();
            delivery.setWarehouse(warehouse);

            List<DeliveryItem> items = request.getItems().stream().map(itemDto -> {
                Product product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                DeliveryItem item = new DeliveryItem();
                item.setProduct(product);
                item.setQuantity(itemDto.getQuantity());
                item.setDelivery(delivery);
                return item;
            }).toList();

            delivery.setItems(items);

            if (!canAddDelivery(warehouse, delivery)) {
                throw new RuntimeException("Not enough free capacity in warehouse");
            }

            Delivery saved = deliveryRepository.save(delivery);

            return DeliveryDto.builder()
                    .id(saved.getId())
                    .warehouseId(saved.getWarehouse().getId())
                    .items(saved.getItems().stream()
                            .map(i -> new DeliveryItemDto(
                                    i.getId(),
                                    i.getDelivery().getId(),
                                    i.getProduct().getId(),
                                    i.getQuantity()
                            ))
                            .toList())
                    .build();
        }

        private boolean canAddDelivery(Warehouse warehouse, Delivery delivery) {
            double requiredVolume = delivery.getItems().stream()
                    .mapToDouble(item -> item.getQuantity() * item.getProduct().getVolumePerUnit())
                    .sum();
            return warehouseService.getFreeCapacity(warehouse) >= requiredVolume;
        }
    }


