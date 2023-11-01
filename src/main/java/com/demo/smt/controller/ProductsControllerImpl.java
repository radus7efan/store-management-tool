package com.demo.smt.controller;

import com.demo.smt.controller.rest.api.ProductsApi;
import com.demo.smt.model.rest.Product;
import com.demo.smt.model.rest.StockStatus;
import com.demo.smt.service.ProductsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store-management")
@RequiredArgsConstructor
public class ProductsControllerImpl implements ProductsApi {

    private final ProductsService productsService;

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Product> fetchProductById(Long productId) {
        return new ResponseEntity<>(
                productsService.fetchProductById(productId),
                HttpStatus.OK
        );
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Product>> fetchProducts(StockStatus stockStatus) {
        return new ResponseEntity<>(
                productsService.fetchProducts(stockStatus),
                HttpStatus.OK
        );
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> addProduct(Product product) {
        return new ResponseEntity<>(
                productsService.addProduct(product),
                HttpStatus.CREATED
        );
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProductById(Long productId, Product product) {
        return new ResponseEntity<>(
                productsService.updateProductById(productId, product),
                HttpStatus.OK
        );
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeProductById(Long productId) {
        productsService.removeProductById(productId);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }
}
