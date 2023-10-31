package com.demo.smt.persistance.repository;

import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.domain.model.StockStatusEnum;
import com.demo.smt.exception.StoreManagementToolException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(Long productId);

    List<ProductEntity> findAllByStockStatus(StockStatusEnum stockStatusEnum);

    ProductEntity save(ProductEntity product);

    default ProductEntity findByIdRequired(Long productId) {
        return findById(productId)
                .orElseThrow(() -> new StoreManagementToolException(HttpStatus.NOT_FOUND,
                        "Could not find the Product with id: %s!", productId));
    }
}
