package com.example.warehouse.repository;

import com.example.warehouse.entity.Delivery;
import com.example.warehouse.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByWarehouseId(Long warehouseId);
}
