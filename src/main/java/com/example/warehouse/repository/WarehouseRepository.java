package com.example.warehouse.repository;

import com.example.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Stream<Warehouse> streamAllBy();

}
