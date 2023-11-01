package com.demo.smt.utils;

import com.demo.smt.model.rest.Inventory;

import java.util.List;

import static com.demo.smt.utils.ProductUtils.createProduct;

public class InventoryUtils {

    public static Inventory createInventory() {
        Inventory inventory = new Inventory();
        inventory.setTotalPrice(123.24f);
        inventory.setTotalPriceWithDiscounts(120.24f);
        inventory.setTotalDiscountedPrice(3f);
        inventory.setDiscountedProducts(1);
        inventory.setInStockProducts(2);
        inventory.setLimitedStockProducts(1);
        inventory.setOutOfStockProducts(1);
        inventory.setTotalQuantity(432);
        inventory.setProducts(List.of(createProduct()));

        return inventory;
    }
}
