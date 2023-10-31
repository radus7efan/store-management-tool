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

@RestController
@RequestMapping("/api/v1/store-management")
@RequiredArgsConstructor
public class ProductsControllerImpl implements ProductsApi {

    private final ProductsService productsService;

    @Override
    public ResponseEntity<Product> fetchProductById(Long productId) {
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

    @Override
    public ResponseEntity<Product> addProduct(Product product) {
        return new ResponseEntity<>(
                productsService.addProduct(product),
                HttpStatus.OK
        );
    }
}
