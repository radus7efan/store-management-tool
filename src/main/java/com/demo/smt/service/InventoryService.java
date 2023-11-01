package com.demo.smt.service;

import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.domain.model.StockStatusEnum;
import com.demo.smt.model.rest.Inventory;
import com.demo.smt.persistance.repository.ProductRepository;
import com.demo.smt.transformer.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.smt.domain.model.StockStatusEnum.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public Inventory getStoreInventory() {
        log.info("Calling method -- getStoreInventory -- ");

        List<ProductEntity> productEntityList = productRepository.findAll();

        var totalPrice = getTotalPriceOfAvailableProducts(productEntityList);
        var totalPriceWithDiscounts = getTotalPriceWithDiscountsOfAvailableProducts(productEntityList);

        Inventory inventory = new Inventory();
        inventory.setTotalPrice(totalPrice);
        inventory.setTotalPriceWithDiscounts(totalPriceWithDiscounts);
        inventory.setTotalDiscountedPrice(totalPrice - totalPriceWithDiscounts);
        inventory.setDiscountedProducts(getNumberOfDiscountedPrices(productEntityList));
        inventory.setInStockProducts(getNumberOfProductsByStatus(productEntityList, IN_STOCK));
        inventory.setLimitedStockProducts(getNumberOfProductsByStatus(productEntityList, LIMITED));
        inventory.setOutOfStockProducts(getNumberOfProductsByStatus(productEntityList, OUT_OF_STOCK));
        inventory.setTotalQuantity(getQuantityOfAvailableProductsByStatus(productEntityList));
        inventory.setProducts(productEntityList.stream().map(productMapper::productEntityToProduct).toList());

        return inventory;
    }

    private Float getTotalPriceOfAvailableProducts(List<ProductEntity> productEntityList) {
        return (float) productEntityList.stream()
                .filter(p -> p.getStockStatus() != OUT_OF_STOCK)
                .mapToDouble(ProductEntity::getPrice)
                .sum();
    }

    private Float getTotalPriceWithDiscountsOfAvailableProducts(List<ProductEntity> productEntityList) {
        return (float) productEntityList.stream()
                .filter(p -> p.getStockStatus() != OUT_OF_STOCK)
                .mapToDouble(ProductEntity::getDiscountedPrice)
                .sum();
    }

    private Integer getNumberOfDiscountedPrices(List<ProductEntity> productEntityList) {
        return (int) productEntityList.stream()
                .filter(p -> p.getDiscount() > 0)
                .count();
    }

    private Integer getQuantityOfAvailableProductsByStatus(List<ProductEntity> productEntityList) {
        return productEntityList.stream()
                .mapToInt(ProductEntity::getQuantity)
                .sum();
    }

    private Integer getNumberOfProductsByStatus(List<ProductEntity> productEntityList, StockStatusEnum status) {
        return (int) productEntityList.stream()
                .filter(p -> p.getStockStatus() == status)
                .count();
    }
}
