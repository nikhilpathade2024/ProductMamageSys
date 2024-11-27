package com.springboot.testcases;

import com.springboot.controller.ProductController;
import com.springboot.dto.ProductDTO;
import com.springboot.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    private ProductDTO sampleProduct;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        sampleProduct = new ProductDTO();
        sampleProduct.setId(1L);
        sampleProduct.setName("Sample Product");
        sampleProduct.setDescription("Sample Description");
        sampleProduct.setPrice(100.0);
        sampleProduct.setQuantity(10);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<ProductDTO> productList = Arrays.asList(sampleProduct);
        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(sampleProduct.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(sampleProduct.getName())));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        when(productService.getProductById(1L)).thenReturn(sampleProduct);

        mockMvc.perform(get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(sampleProduct.getId().intValue())))
                .andExpect(jsonPath("$.name", is(sampleProduct.getName())));

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testCreateProduct() throws Exception {
        when(productService.createProduct(any(ProductDTO.class))).thenReturn(sampleProduct);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Sample Product\", \"description\": \"Sample Description\", \"price\": 100.0, \"quantity\": 10 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(sampleProduct.getId().intValue())))
                .andExpect(jsonPath("$.name", is(sampleProduct.getName())));

        verify(productService, times(1)).createProduct(any(ProductDTO.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(productService.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(sampleProduct);

        mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Updated Product\", \"description\": \"Updated Description\", \"price\": 150.0, \"quantity\": 20 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(sampleProduct.getId().intValue())))
                .andExpect(jsonPath("$.name", is(sampleProduct.getName())));

        verify(productService, times(1)).updateProduct(eq(1L), any(ProductDTO.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(1L);
    }
}
