package com.inventory.inventoryservice.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.inventoryservice.dto.InventoryResponse;
import com.inventory.inventoryservice.model.Inventory;
import com.inventory.inventoryservice.repository.InventoryRepository;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).get().getQuantity()>0;
    }

    public List<InventoryResponse> findAll(){
        return inventoryRepository.findAll().stream().map(this::mapToInventoryResponseDto).toList();
    }
    public InventoryResponse mapToInventoryResponseDto(Inventory inventory){
        return new InventoryResponse().builder().id(inventory.getId()).quantity(inventory.getQuantity()).skuCode(inventory.getSkuCode()).build();
    }
}
