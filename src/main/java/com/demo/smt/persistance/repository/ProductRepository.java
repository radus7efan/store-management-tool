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

    List<ProductEntity> findAllByStockStatus(StockStatusEnum status);

    List<ProductEntity> findAll();

    ProductEntity save(ProductEntity product);

    void deleteById(Long productId);

    default ProductEntity findByIdRequired(Long productId) {
        return findById(productId)
                .orElseThrow(() -> new StoreManagementToolException(HttpStatus.NOT_FOUND,
                        "Could not find the Product with id: %s!", productId));
    }

    default List<ProductEntity> findAllByStatus(StockStatusEnum status) {
        return status == null ? findAll() : findAllByStockStatus(status);
    }
}
