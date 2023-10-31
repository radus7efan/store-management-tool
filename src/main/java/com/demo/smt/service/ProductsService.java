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

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final StockStatusMapper stockStatusMapper;

    public Product fetchProductById(Long productId) {
        log.info("Calling method -- fetchProductById -- with id: {}", productId);

        ProductEntity product = productRepository.findByIdRequired(productId);

        return productMapper.productEntityToProduct(product);
    }

    public List<Product> fetchProducts(StockStatus stockStatus) {
        log.info("Calling method -- fetchProducts -- with stockStatus: {}", stockStatus);

        StockStatusEnum stockStatusValue = stockStatusMapper.stockStatusToStockStatusEnum(stockStatus);
        List<ProductEntity> products = productRepository.findAllByStatus(stockStatusValue);

        return products.stream()
                .map(productMapper::productEntityToProduct)
                .toList();
    }

    public Product addProduct(Product product) {
        log.info("Calling method -- addProduct -- product: {}", product);

        ProductEntity newProduct = productMapper.productToProductEntity(product);
        productRepository.save(newProduct);

        return productMapper.productEntityToProduct(newProduct);
    }

    public Product updateProductById(Long productId, Product updatedProduct) {
        log.info("Calling method -- updateProductById -- with productId: {} and product: {}", productId, updatedProduct);

        ProductEntity product = productRepository.findByIdRequired(productId);
        productMapper.mapProduct(product, updatedProduct);
        product = productRepository.save(product);

        return productMapper.productEntityToProduct(product);
    }

    public void removeProductById(Long productId) {
        log.info("Calling method -- removeProductById -- with productId: {}", productId);

        ProductEntity product = productRepository.findByIdRequired(productId);
        productRepository.deleteById(product.getId());
    }
}
