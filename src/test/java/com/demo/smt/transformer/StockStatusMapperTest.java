package com.demo.smt.transformer;

import com.demo.smt.domain.model.StockStatusEnum;
import com.demo.smt.model.rest.StockStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {StockStatusMapperImpl.class})
@ExtendWith(SpringExtension.class)
class StockStatusMapperTest {

    @Autowired
    private StockStatusMapper stockStatusMapper;

    private static Stream<Arguments> provideArgumentsForStockStatusToStockStatusEnum() {
        return Stream.of(
             Arguments.of(StockStatus.IN_STOCK, StockStatusEnum.IN_STOCK),
             Arguments.of(StockStatus.OUT_OF_STOCK, StockStatusEnum.OUT_OF_STOCK),
             Arguments.of(StockStatus.LIMITED, StockStatusEnum.LIMITED),
             Arguments.of(null, null)
        );
    }

    private static Stream<Arguments> provideArgumentsForStockStatusEnumToStockStatus() {
        return Stream.of(
                Arguments.of(StockStatusEnum.IN_STOCK, StockStatus.IN_STOCK),
                Arguments.of(StockStatusEnum.OUT_OF_STOCK, StockStatus.OUT_OF_STOCK),
                Arguments.of(StockStatusEnum.LIMITED, StockStatus.LIMITED),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForStockStatusToStockStatusEnum")
    void stockStatusToStockStatusEnum(StockStatus input, StockStatusEnum expected) {
        var result = stockStatusMapper.stockStatusToStockStatusEnum(input);

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForStockStatusEnumToStockStatus")
    void stockStatusEnumToStockStatus(StockStatusEnum input, StockStatus expected) {
        var result = stockStatusMapper.stockStatusEnumToStockStatus(input);

        assertEquals(expected, result);
    }
}