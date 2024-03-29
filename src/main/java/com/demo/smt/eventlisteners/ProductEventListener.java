package com.demo.smt.eventlisteners;

import com.demo.smt.domain.model.ProductEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.demo.smt.domain.model.StockStatusEnum.*;

@Slf4j
@Component
public class ProductEventListener {

    private static final String PRODUCT_STATUS_LOG = "Product: {} with id: {} has new stock status: {}.";

    @PrePersist
    @PreUpdate
    public void onPrePersist(ProductEntity product) {
        setDiscountedPrice(product);
        setProductStockStatus(product);
    }

    private void setDiscountedPrice(ProductEntity product) {
        if (product.getDiscount() > 0) {
            product.setDiscountedPrice(product.getPrice() - (1 - (product.getDiscount() / 100f)));
        }
        product.setDiscountedPrice(product.getPrice());
    }

    private void setProductStockStatus(ProductEntity product) {
        var quantity = product.getQuantity();
        if (quantity == 0) {
            log.info(PRODUCT_STATUS_LOG, product.getTitle(), product.getId(), OUT_OF_STOCK);
            product.setStockStatus(OUT_OF_STOCK);
            return;
        }
        if (quantity < 5) {
            log.info(PRODUCT_STATUS_LOG, product.getTitle(), product.getId(), LIMITED);
            product.setStockStatus(LIMITED);
            return;
        }
        if (quantity > 5) {
            log.info(PRODUCT_STATUS_LOG, product.getTitle(), product.getId(), IN_STOCK);
            product.setStockStatus(IN_STOCK);
        }
    }
}