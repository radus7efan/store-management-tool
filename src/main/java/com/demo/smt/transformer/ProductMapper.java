package com.demo.smt.transformer;

import com.demo.smt.config.MappersConfig;
import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.model.rest.Product;
import org.mapstruct.Mapper;

/**
 * Product mapper class.
 */
@Mapper(
        componentModel = "spring",
        config = MappersConfig.class,
        uses = {
                CommonMapper.class, StockStatusMapper.class
        }
)
public interface ProductMapper {

    /**
     * Mapping method from {@link Product} to {@link ProductEntity}.
     *
     * @param product instance of {@link Product}
     * @return an instance of {@link ProductEntity}
     */
    ProductEntity productToProductEntity(Product product);

    /**
     * Mapping method from {@link ProductEntity} to {@link Product}.
     *
     * @param product instance of {@link ProductEntity}
     * @return an instance of {@link Product}
     */
    Product productEntityToProduct(ProductEntity product);

}
