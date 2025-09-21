package com.example.warehouse.controller;

import com.example.warehouse.dto.DeliveryDto;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.service.DeliveryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeliveryController {


    private final DeliveryService deliveryService;
//    http://localhost:8282/api/delivery
    @PostMapping
    public ResponseEntity<DeliveryDto> createDelivery(@RequestBody DeliveryDto request) {
        DeliveryDto delivery = deliveryService.createDelivery(request);
        return ResponseEntity.ok(delivery);
    }




}
