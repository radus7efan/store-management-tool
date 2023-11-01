package com.demo.smt.controller;

import com.demo.smt.controller.rest.api.InventoryApi;
import com.demo.smt.model.rest.Inventory;
import com.demo.smt.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store-management")
@RequiredArgsConstructor
public class InventoryControllerImpl implements InventoryApi {

    private final InventoryService inventoryService;

    @Override
    public ResponseEntity<Inventory> getStoreInventory() {
        return new ResponseEntity<>(
                inventoryService.getStoreInventory(),
                HttpStatus.OK
        );
    }
}
