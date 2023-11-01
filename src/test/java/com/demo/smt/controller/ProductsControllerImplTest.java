package com.demo.smt.controller;

import com.demo.smt.exception.StoreManagementToolException;
import com.demo.smt.model.rest.Product;
import com.demo.smt.service.ProductsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.demo.smt.utils.ProductUtils.createProduct;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ProductsControllerImplTest {

    private static final String PATH = "/api/v1/store-management/products";

    private final Faker faker = Faker.instance();

    @MockBean
    private ProductsService productsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fetchProductById() throws Exception {
        var product = createProduct();

        when(productsService.fetchProductById(product.getId())).thenReturn(product);

        mockMvc.perform(get(PATH + "/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.title").value(product.getTitle()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.quantity").value(product.getQuantity()))
                .andExpect(jsonPath("$.discount").value(product.getDiscount()))
                .andExpect(jsonPath("$.discountedPrice").value(product.getDiscountedPrice()))
                .andExpect(jsonPath("$.description").value(product.getDescription()));
    }

    @Test
    void fetchProductByNonExistingId() throws Exception {
        var product = createProduct();
        var ex = new StoreManagementToolException(HttpStatus.NOT_FOUND,
                "Could not find the Product with id: %s!", product.getId());

        when(productsService.fetchProductById(product.getId())).thenThrow(ex);

        mockMvc.perform(get(PATH + "/" + product.getId()))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.title").value(ex.getStatus().name()))
                .andExpect(jsonPath("$.status").value(ex.getStatus().value()))
                .andExpect(jsonPath("$.details").value(ex.getMessage()));
    }

    @Test
    void fetchProductByInvalidId() throws Exception {
        mockMvc.perform(get(PATH + "/sadas"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.title").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.details").isNotEmpty());
    }

    @Test
    void fetchAllProducts() throws Exception {
        var product = createProduct();

        when(productsService.fetchProducts(null)).thenReturn(List.of(product));

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0]id").value(product.getId()))
                .andExpect(jsonPath("$[0].title").value(product.getTitle()))
                .andExpect(jsonPath("$[0].price").value(product.getPrice()))
                .andExpect(jsonPath("$[0].quantity").value(product.getQuantity()))
                .andExpect(jsonPath("$[0].discount").value(product.getDiscount()))
                .andExpect(jsonPath("$[0].discountedPrice").value(product.getDiscountedPrice()))
                .andExpect(jsonPath("$[0].description").value(product.getDescription()));
    }

    @Test
    void fetchAllProductsByStatus() throws Exception {
        var product = createProduct();

        when(productsService.fetchProducts(product.getStockStatus())).thenReturn(List.of(product));

        mockMvc.perform(get(PATH).queryParam("stockStatus", product.getStockStatus().getValue()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id").value(product.getId()))
                .andExpect(jsonPath("$[0].title").value(product.getTitle()))
                .andExpect(jsonPath("$[0].price").value(product.getPrice()))
                .andExpect(jsonPath("$[0].quantity").value(product.getQuantity()))
                .andExpect(jsonPath("$[0].discount").value(product.getDiscount()))
                .andExpect(jsonPath("$[0].discountedPrice").value(product.getDiscountedPrice()))
                .andExpect(jsonPath("$[0].description").value(product.getDescription()));
    }

    @Test
    void addProduct() throws Exception {
        var product = createProduct();

        when(productsService.addProduct(product)).thenReturn(product);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(product)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.title").value(product.getTitle()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.quantity").value(product.getQuantity()))
                .andExpect(jsonPath("$.discount").value(product.getDiscount()))
                .andExpect(jsonPath("$.discountedPrice").value(product.getDiscountedPrice()))
                .andExpect(jsonPath("$.description").value(product.getDescription()));
    }

    @Test
    void addInvalidProduct() throws Exception {
        var product = createProduct();
        product.setId(null);
        product.setPrice(null);

        when(productsService.addProduct(product)).thenThrow(new DataIntegrityViolationException("null"));

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(product)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.title").value(HttpStatus.INTERNAL_SERVER_ERROR.name()))
                .andExpect(jsonPath("$.status").value(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(jsonPath("$.details").isNotEmpty());
    }

    @Test
    void updateProductById() throws Exception {
        var product = createProduct();

        Product updatedProduct = new Product();
        updatedProduct.setTitle(faker.superhero().name());
        updatedProduct.setDescription(faker.yoda().quote());

        product.setTitle(updatedProduct.getTitle());
        product.setDescription(updatedProduct.getDescription());

        when(productsService.updateProductById(product.getId(), updatedProduct)).thenReturn(product);

        mockMvc.perform(put(PATH + "/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.title").value(updatedProduct.getTitle()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.quantity").value(product.getQuantity()))
                .andExpect(jsonPath("$.discount").value(product.getDiscount()))
                .andExpect(jsonPath("$.discountedPrice").value(product.getDiscountedPrice()))
                .andExpect(jsonPath("$.description").value(updatedProduct.getDescription()));
    }

    @Test
    void removeProductById() throws Exception {
        var product = createProduct();

        doNothing().when(productsService).removeProductById(product.getId());

        mockMvc.perform(delete(PATH + "/" + product.getId()))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse object.");
        }
    }
}