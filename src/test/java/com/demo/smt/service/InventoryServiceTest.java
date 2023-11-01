package com.demo.smt.service;

import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.domain.model.StockStatusEnum;
import com.demo.smt.model.rest.Product;
import com.demo.smt.model.rest.StockStatus;
import com.demo.smt.persistance.repository.ProductRepository;
import com.demo.smt.transformer.ProductMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.demo.smt.utils.ProductUtils.createProduct;
import static com.demo.smt.utils.ProductUtils.createProductEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    private final Faker faker = Faker.instance();

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void getStoreInventory() {
        List<ProductEntity> products = new ArrayList<>();
        ProductEntity productEOne = createProductEntity();
        ProductEntity productETwo = createProductEntity();
        productETwo.setPrice(423.1f);
        productETwo.setQuantity(421);
        productETwo.setDiscount(32);
        productETwo.setStockStatus(StockStatusEnum.LIMITED);
        products.add(productEOne);
        products.add(productETwo);
        Product productOne = createProduct();
        Product productTwo = createProduct();
        productTwo.setPrice(423.1f);
        productTwo.setQuantity(421);
        productTwo.setDiscount(32);
        productTwo.setStockStatus(StockStatus.LIMITED);

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.productEntityToProduct(productEOne)).thenReturn(productOne);
        when(productMapper.productEntityToProduct(productETwo)).thenReturn(productTwo);

        var result = inventoryService.getStoreInventory();


        var discountedPrice = (productOne.getPrice() - productOne.getDiscountedPrice()) +
                (productTwo.getPrice() - productTwo.getDiscountedPrice());

        assertEquals(Math.round(discountedPrice), Math.round(result.getTotalDiscountedPrice()));
        assertEquals(productOne.getDiscountedPrice() + productTwo.getDiscountedPrice(), result.getTotalPriceWithDiscounts());
        assertEquals(1, result.getDiscountedProducts());
        assertEquals(productOne.getPrice() + productTwo.getPrice(), result.getTotalPrice());
        assertEquals(productOne.getQuantity() + productTwo.getQuantity(), result.getTotalQuantity());
        assertEquals(1, result.getInStockProducts());
        assertEquals(1, result.getLimitedStockProducts());
        assertEquals(0, result.getOutOfStockProducts());
        assertEquals(2, result.getProducts().size());

    }
}