package com.example.warehouse.controller;

import com.example.warehouse.dto.ShipmentDto;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Shipment;
import com.example.warehouse.repository.ShipmentRepository;
import com.example.warehouse.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/shipments")
public class ShipmentController {


    private final ShipmentService shipmentService;

//    http://localhost:8282/api/shipments/create
    @PostMapping("/create")
    public ResponseEntity<ShipmentDto> createShipment(@RequestBody ShipmentDto shipmentDto) {
        ShipmentDto createdShipment = shipmentService.createShipment(shipmentDto);
        return ResponseEntity.ok(createdShipment);
    }
}
