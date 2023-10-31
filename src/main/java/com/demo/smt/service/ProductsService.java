package com.demo.smt.service;

import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.model.rest.Product;
import com.demo.smt.persistance.repository.ProductRepository;
import com.demo.smt.transformer.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public Product fetchProductById(UUID uuid) {
        log.info("Calling method -- fetchProductById -- with uuid: {}", uuid);
        ProductEntity productEntity = productRepository.findByIdRequired(uuid.toString());
        return productMapper.productEntityToProduct(productEntity);
    }

}
