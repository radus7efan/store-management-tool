package com.demo.smt.transformer;

import com.demo.smt.config.MappersConfig;
import com.demo.smt.domain.model.StockStatusEnum;
import com.demo.smt.model.rest.StockStatus;
import org.mapstruct.Mapper;

/**
 * Stock status mapper class.
 */
@Mapper(componentModel = "spring", config = MappersConfig.class)
public interface StockStatusMapper {

    /**
     * Mapping method from {@link StockStatus} to {@link StockStatusEnum}.
     *
     * @param status instance of {@link StockStatus}
     * @return an instance of {@link StockStatusEnum}
     */
    StockStatusEnum stockStatusToStockStatusEnum(StockStatus status);

    /**
     * Mapping method from {@link StockStatusEnum} to {@link StockStatus}.
     *
     * @param status instance of {@link StockStatusEnum}
     * @return an instance of {@link StockStatus}
     */
    StockStatus stockStatusEnumToStockStatus(StockStatusEnum status);

}
