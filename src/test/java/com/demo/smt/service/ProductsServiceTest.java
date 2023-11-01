package com.demo.smt.service;

import com.demo.smt.domain.model.ProductEntity;
import com.demo.smt.domain.model.StockStatusEnum;
import com.demo.smt.exception.StoreManagementToolException;
import com.demo.smt.model.rest.Product;
import com.demo.smt.model.rest.StockStatus;
import com.demo.smt.persistance.repository.ProductRepository;
import com.demo.smt.transformer.ProductMapper;
import com.demo.smt.transformer.StockStatusMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.demo.smt.utils.ProductUtils.createProduct;
import static com.demo.smt.utils.ProductUtils.createProductEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {

    private final Faker faker = Faker.instance();

    @Mock
    private ProductMapper productMapper;

    @Mock
    private StockStatusMapper stockStatusMapper;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductsService productsService;

    @Test
    void fetchProductById() {
        ProductEntity productEntity = createProductEntity();
        Product product = createProduct();

        when(productRepository.findByIdRequired(product.getId())).thenReturn(productEntity);
        when(productMapper.productEntityToProduct(productEntity)).thenReturn(product);

        var result = productsService.fetchProductById(product.getId());

        assertProduct(product, result);
    }

    @Test
    void fetchProductByNonExistentId() {
        Product product = createProduct();

        when(productRepository.findByIdRequired(product.getId()))
                .thenThrow(new StoreManagementToolException(HttpStatus.NOT_FOUND,
                        "Could not find the Product with id: %s!", product.getId()));

        var result = assertThrows(StoreManagementToolException.class, () -> productsService.fetchProductById(product.getId()));

        assertEquals(String.format("Could not find the Product with id: %s!", product.getId()), result.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, result.getStatus());
    }

    @Test
    void fetchAllProducts() {
        ProductEntity productEntity = createProductEntity();
        Product product = createProduct();

        when(stockStatusMapper.stockStatusToStockStatusEnum(null)).thenReturn(null);
        when(productRepository.findAllByStatus(null)).thenReturn(List.of(productEntity));
        when(productMapper.productEntityToProduct(productEntity)).thenReturn(product);

        var result = productsService.fetchProducts(null);

        assertEquals(1, result.size());
        assertProduct(product, result.get(0));
    }

    @Test
    void fetchProductsByStatus() {
        ProductEntity productEntity = createProductEntity();
        Product product = createProduct();

        when(stockStatusMapper.stockStatusToStockStatusEnum(StockStatus.IN_STOCK)).thenReturn(StockStatusEnum.IN_STOCK);
        when(productRepository.findAllByStatus(StockStatusEnum.IN_STOCK)).thenReturn(List.of(productEntity));
        when(productMapper.productEntityToProduct(productEntity)).thenReturn(product);

        var result = productsService.fetchProducts(StockStatus.IN_STOCK);

        assertEquals(1, result.size());
        assertProduct(product, result.get(0));
    }

    @Test
    void addProduct() {
        ProductEntity productEntity = createProductEntity();
        Product product = createProduct();

        when(productMapper.productToProductEntity(product)).thenReturn(productEntity);
        when(productRepository.save(productEntity)).thenReturn(productEntity);
        when(productMapper.productEntityToProduct(productEntity)).thenReturn(product);

        var result = productsService.addProduct(product);

        assertProduct(product, result);
    }

    @Test
    void updateProductById() {
        ProductEntity productEntity = createProductEntity();
        Product product = new Product();
        product.setTitle(faker.superhero().name());
        product.setDescription(faker.yoda().quote());

        when(productRepository.findByIdRequired(1L)).thenReturn(productEntity);
        when(productRepository.save(productEntity)).thenReturn(productEntity);
        when(productMapper.productEntityToProduct(productEntity)).thenReturn(product);

        var result = productsService.updateProductById(1L, product);

        assertEquals(product.getTitle(), result.getTitle());
        assertEquals(product.getDescription(), result.getDescription());
    }

    @Test
    void removeProductById() {
        ProductEntity productEntity = createProductEntity();

        when(productRepository.findByIdRequired(1L)).thenReturn(productEntity);
        doNothing().when(productRepository).deleteById(1L);

        productsService.removeProductById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    private void assertProduct(Product actual, Product expected) {
        assertEquals(actual.getTitle(), expected.getTitle());
        assertEquals(actual.getPrice(), expected.getPrice());
        assertEquals(actual.getQuantity(), expected.getQuantity());
        assertEquals(actual.getDiscount(), expected.getDiscount());
        assertEquals(actual.getDiscountedPrice(), expected.getDiscountedPrice());
        assertEquals(actual.getStockStatus().name(), expected.getStockStatus().getValue());
        assertEquals(actual.getDescription(), expected.getDescription());
    }
}