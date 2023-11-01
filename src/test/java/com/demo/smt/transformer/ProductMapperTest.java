package com.demo.smt.transformer;

import com.demo.smt.domain.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.demo.smt.utils.ProductUtils.createProduct;
import static com.demo.smt.utils.ProductUtils.createProductEntity;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {ProductMapperImpl.class, StockStatusMapperImpl.class})
@ExtendWith(SpringExtension.class)
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void nullProductToProductEntity() {
        var result = productMapper.productToProductEntity(null);

        assertNull(result);
    }

    @Test
    void productToProductEntity() {
        var product = createProduct();

        var result = productMapper.productToProductEntity(product);

        assertEquals(product.getTitle(), result.getTitle());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getQuantity(), result.getQuantity());
        assertEquals(product.getDiscount(), result.getDiscount());
        assertEquals(product.getDescription(), result.getDescription());
    }

    @Test
    void nullProductEntityToProduct() {
        var result = productMapper.productEntityToProduct(null);

        assertNull(result);
    }

    @Test
    void productEntityToProduct() {
        var product = createProductEntity();

        var result = productMapper.productEntityToProduct(product);

        assertEquals(product.getTitle(), result.getTitle());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getQuantity(), result.getQuantity());
        assertEquals(product.getDiscount(), result.getDiscount());
        assertEquals(product.getDiscountedPrice(), result.getDiscountedPrice());
        assertEquals(product.getStockStatus().name(), result.getStockStatus().getValue());
        assertEquals(product.getDescription(), result.getDescription());
    }

    @Test
    void mapProduct() {
        var product = createProduct();
        var productEntity = new ProductEntity();

        productMapper.mapProduct(productEntity, product);

        assertEquals(product.getTitle(), productEntity.getTitle());
        assertEquals(product.getPrice(), productEntity.getPrice());
        assertEquals(product.getQuantity(), productEntity.getQuantity());
        assertEquals(product.getDiscount(), productEntity.getDiscount());
        assertEquals(product.getDiscountedPrice(), productEntity.getDiscountedPrice());
        assertEquals(product.getStockStatus().getValue(), productEntity.getStockStatus().name());
        assertEquals(product.getDescription(), productEntity.getDescription());
    }
}