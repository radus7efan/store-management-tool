package com.demo.smt.controller;

import com.demo.smt.controller.rest.api.ItemsApi;
import com.demo.smt.model.rest.Item;
import com.demo.smt.service.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class ItemsControllerImpl implements ItemsApi {

    private final ItemsService itemsService;

    @Override
    public ResponseEntity<Item> fetchItemById(UUID itemId) {
        return new ResponseEntity<>(
                itemsService.fetchItemById(itemId),
                HttpStatus.OK
        );
    }
}
