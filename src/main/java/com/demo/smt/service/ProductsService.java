package com.demo.smt.service;

import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.domain.model.StockStatusEnum;
import com.demo.smt.model.rest.Product;
import com.demo.smt.model.rest.StockStatus;
import com.demo.smt.persistance.repository.ProductRepository;
import com.demo.smt.transformer.ProductMapper;
import com.demo.smt.transformer.StockStatusMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final StockStatusMapper stockStatusMapper;

    public Product fetchProductById(UUID uuid) {
        log.info("Calling method -- fetchProductById -- with uuid: {}", uuid);

        ProductEntity product = productRepository.findByIdRequired(uuid.toString());

        return productMapper.productEntityToProduct(product);
    }

    public List<Product> fetchProducts(StockStatus stockStatus) {
        log.info("Calling method -- fetchProducts -- with stockStatus: {}", stockStatus);

        StockStatusEnum stockStatusValue = stockStatusMapper.stockStatusToStockStatusEnum(stockStatus);
        List<ProductEntity> products = productRepository.findAllByStockStatus(stockStatusValue);

        return products.stream()
                .map(productMapper::productEntityToProduct)
                .toList();
    }

    public Product addProduct(Product product) {
        log.info("Calling method -- addProduct -- with uuid: {}", product);

        ProductEntity newProduct = productMapper.productToProductEntity(product);
        productRepository.insert(newProduct);

        return productMapper.productEntityToProduct(newProduct);

    }
}
