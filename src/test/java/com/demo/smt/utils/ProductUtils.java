package com.demo.smt.utils;

import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.domain.model.StockStatusEnum;
import com.demo.smt.model.rest.Product;
import com.demo.smt.model.rest.StockStatus;
import com.github.javafaker.Faker;

public class ProductUtils {

    private static final Faker faker = Faker.instance();

    public static ProductEntity createProductEntity() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setTitle(faker.book().title());
        product.setPrice(123.12f);
        product.setQuantity(20);
        product.setStockStatus(StockStatusEnum.IN_STOCK);
        product.setDiscount(0);
        product.setDiscountedPrice(123.12f);
        product.setDescription(faker.book().author());

        return product;
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle(faker.book().title());
        product.setPrice(123.12f);
        product.setQuantity(20);
        product.setStockStatus(StockStatus.IN_STOCK);
        product.setDiscount(0);
        product.setDiscountedPrice(123.12f);
        product.setDescription(faker.book().author());

        return product;
    }
}
