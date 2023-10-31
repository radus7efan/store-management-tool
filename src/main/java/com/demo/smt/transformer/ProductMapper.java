package com.demo.smt.transformer;

import com.demo.smt.config.MappersConfig;
import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.model.rest.Product;
import org.mapstruct.Mapper;

/**
 * Item mapper class.
 */
@Mapper(
        componentModel = "spring",
        config = MappersConfig.class,
        uses = {
                CommonMapper.class
        }
)
public interface ProductMapper {

    /**
     * Mapping method from {@link Product} to {@link ProductEntity}.
     *
     * @param item instance of {@link Product}
     * @return an instance of {@link ProductEntity}
     */
    ProductEntity productToProductEntity(Product item);

    /**
     * Mapping method from {@link ProductEntity} to {@link Product}.
     *
     * @param item instance of {@link ProductEntity}
     * @return an instance of {@link Product}
     */
    Product productEntityToProduct(ProductEntity item);

}
