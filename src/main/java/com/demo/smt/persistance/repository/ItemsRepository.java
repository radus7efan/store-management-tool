package com.demo.smt.persistance.repository;

import com.demo.smt.domain.model.ItemEntity;
import com.demo.smt.exception.StoreManagementToolException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemsRepository extends MongoRepository<ItemEntity, String> {

    Optional<ItemEntity> findById(String uuid);

    default ItemEntity findByIdRequired(String uuid) {
        return findById(uuid)
                .orElseThrow(() -> new StoreManagementToolException(HttpStatus.NOT_FOUND,
                        "Could not find the Item with id: %s!", uuid));
    }
}
