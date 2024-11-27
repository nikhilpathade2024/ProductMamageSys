package com.springboot.testcases;

import com.springboot.dto.ProductDTO;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Product;
import com.springboot.repository.ProductRepository;
import com.springboot.seviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        product.setName("Sample Product");
        product.setDescription("Sample Description");
        product.setPrice(100.0);
        product.setQuantity(10);
    }

    @Test
    public void testGetProductById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductDTO productDTO = productService.getProductById(1L);

        assertNotNull(productDTO);
        assertEquals("Sample Product", productDTO.getName());
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1L));
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("New Product");
        productDTO.setDescription("New Description");
        productDTO.setPrice(200.0);
        productDTO.setQuantity(20);

        ProductDTO createdProduct = productService.createProduct(productDTO);
        assertNotNull(createdProduct);
        assertEquals("Sample Product", createdProduct.getName());
    }
}
