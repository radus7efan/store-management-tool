package com.demo.smt.persistance.repository;

import com.demo.smt.domain.model.UserEntity;
import com.demo.smt.exception.StoreManagementToolException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    default UserEntity findByUsernameRequired(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new StoreManagementToolException(HttpStatus.NOT_FOUND,
                        "Could not find the User with id: %s!", username));
    }

}
