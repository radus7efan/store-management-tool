package com.demo.smt.persistance.repository;

import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.domain.model.StockStatusEnum;
import com.demo.smt.exception.StoreManagementToolException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    Optional<ProductEntity> findById(String uuid);

    List<ProductEntity> findAllByStockStatus(StockStatusEnum stockStatusEnum);

    default ProductEntity findByIdRequired(String uuid) {
        return findById(uuid)
                .orElseThrow(() -> new StoreManagementToolException(HttpStatus.NOT_FOUND,
                        "Could not find the Product with id: %s!", uuid));
    }
}
