package com.demo.smt.controller;

import com.demo.smt.controller.rest.api.ProductsApi;
import com.demo.smt.model.rest.Product;
import com.demo.smt.model.rest.StockStatus;
import com.demo.smt.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class ProductsControllerImpl implements ProductsApi {

    private final ProductsService productsService;

    @Override
    public ResponseEntity<Product> fetchProductById(UUID productId) {
        return new ResponseEntity<>(
                productsService.fetchProductById(productId),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<List<Product>> fetchProducts(StockStatus stockStatus) {
        return new ResponseEntity<>(
                productsService.fetchProducts(stockStatus),
                HttpStatus.OK
        );
    }
}
