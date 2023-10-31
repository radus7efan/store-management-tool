package com.demo.smt.service;

import com.demo.smt.domain.model.ItemEntity;
import com.demo.smt.model.rest.Item;
import com.demo.smt.persistance.repository.ItemsRepository;
import com.demo.smt.transformer.ItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;

    private final ItemMapper itemMapper;

    public Item fetchItemById(UUID uuid) {
        log.info("Calling method -- fetchItemById -- with uuid: {}", uuid);
        ItemEntity itemEntity = itemsRepository.findByIdRequired(uuid.toString());
        return itemMapper.itemEntityToItem(itemEntity);
    }

}
