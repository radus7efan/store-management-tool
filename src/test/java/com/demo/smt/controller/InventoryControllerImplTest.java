package com.demo.smt.controller;

import com.demo.smt.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.demo.smt.utils.InventoryUtils.createInventory;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class InventoryControllerImplTest {

    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStoreInventory() throws Exception {
        var inventory = createInventory();

        when(inventoryService.getStoreInventory()).thenReturn(inventory);

        mockMvc.perform(get("/api/v1/store-management/inventory"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.totalPrice").value(inventory.getTotalPrice()))
                .andExpect(jsonPath("$.totalPriceWithDiscounts").value(inventory.getTotalPriceWithDiscounts()))
                .andExpect(jsonPath("$.totalDiscountedPrice").value(inventory.getTotalDiscountedPrice()))
                .andExpect(jsonPath("$.inStockProducts").value(inventory.getInStockProducts()))
                .andExpect(jsonPath("$.limitedStockProducts").value(inventory.getLimitedStockProducts()))
                .andExpect(jsonPath("$.totalQuantity").value(inventory.getTotalQuantity()));
    }
}
