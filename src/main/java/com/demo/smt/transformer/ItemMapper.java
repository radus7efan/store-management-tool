package com.demo.smt.transformer;

import com.demo.smt.config.MappersConfig;
import com.demo.smt.domain.model.ItemEntity;
import com.demo.smt.model.rest.Item;
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
public interface ItemMapper {

    /**
     * Mapping method from {@link Item} to {@link ItemEntity}.
     *
     * @param item instance of {@link Item}
     * @return an instance of {@link ItemEntity}
     */
    ItemEntity itemToItemEntity(Item item);

    /**
     * Mapping method from {@link ItemEntity} to {@link Item}.
     *
     * @param item instance of {@link ItemEntity}
     * @return an instance of {@link Item}
     */
    Item itemEntityToItem(ItemEntity item);

}
