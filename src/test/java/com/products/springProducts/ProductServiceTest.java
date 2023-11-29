package com.products.springProducts;

import com.products.springProducts.mapper.ProductMapper;
import com.products.springProducts.model.Product;
import com.products.springProducts.model.ProductDTO;
import com.products.springProducts.repository.ProductRepository;
import com.products.springProducts.service.ProductService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductMapper productMapper;


    @Test
    public void testGetProductDTOById() {
        // Create a test product
        Product product = new Product();
        product.setId(1L);
        product.setName("TestProduct");
        product.setPrice("12");
        product.setDescription("Test description");

        // Mock the behavior of the repository
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Call the service method
        ProductDTO productDTO = productService.getProductDTOById(1L);

        // Assert the results
        assertNotNull(productDTO);
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getDescription(), productDTO.getDescription());

        // Verify that the repository method was called
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteById() {
        // Mock the behavior of the repository
        doNothing().when(productRepository).deleteById(1L);

        // Call the service method
        productService.deleteById(1L);

        // Verify that the repository method was called
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllProducts() {
        // Create some test products
        Product product1 = new Product(1L, "Product1", "12", "Description1");
        Product product2 = new Product(2L, "Product2", "11", "Description2");
        List<Product> mockProducts = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(mockProducts);

        productRepository.saveAll(List.of(product1, product2));


        // Call the service method
        List<ProductDTO> productDTOList = productService.getAllProducts();

        // Assert the results
        assertEquals(2, productDTOList.size());
    }


    @Test
    public void testUpdateProduct() {
        // Create a test ProductDTO
        ProductDTO updatedProductDTO = new ProductDTO();
        updatedProductDTO.setId(1L);
        updatedProductDTO.setName("UpdatedProduct");
        updatedProductDTO.setPrice("15 RON");
        updatedProductDTO.setDescription("Updated description");

        // Create a mock Product for the repository
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("TestProduct");
        mockProduct.setPrice("10 RON");
        mockProduct.setDescription("Test description");

        // Mock the behavior of the repository
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Mock the behavior of the mapper if needed
        when(productMapper.ProductToProductDTOProduct(any())).thenAnswer(invocation -> {
            Product inputProduct = invocation.getArgument(0);
            ProductDTO mappedProductDTO = new ProductDTO();
            mappedProductDTO.setId(inputProduct.getId());
            mappedProductDTO.setName(inputProduct.getName());
            mappedProductDTO.setPrice(inputProduct.getPrice());
            mappedProductDTO.setDescription(inputProduct.getDescription());
            return mappedProductDTO;
        });

        // Call the service method
        ProductDTO updatedProduct = productService.updateProduct(updatedProductDTO);

        // Assert the results
        assertNotNull(updatedProduct);
        assertEquals(updatedProductDTO.getId(), updatedProduct.getId());
        assertEquals(updatedProductDTO.getName(), updatedProduct.getName());
        assertEquals(updatedProductDTO.getPrice(), updatedProduct.getPrice());
        assertEquals(updatedProductDTO.getDescription(), updatedProduct.getDescription());

        // Verify that the repository method was called
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void testChangePrice() {
        // Create a test ProductDTO
        ProductDTO updatedProductDTO = new ProductDTO();
        updatedProductDTO.setId(1L);
        updatedProductDTO.setName("TestProduct");
        updatedProductDTO.setPrice("15");

        // Mock the behavior of the repository
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("TestProduct");  // Make sure the name is not null
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the service method
        ProductDTO updatedProduct = productService.changePrice(updatedProductDTO);

        // Assert the results
        assertNotNull(updatedProduct);
        assertEquals(updatedProductDTO.getId(), updatedProduct.getId());
        assertEquals(updatedProductDTO.getPrice(), updatedProduct.getPrice());

        // Verify that the repository method was called
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void testCreateProduct() {
        // Create a test ProductDTO
        ProductDTO newProductDTO = new ProductDTO();
        newProductDTO.setName("NewProduct");
        newProductDTO.setPrice("25");
        newProductDTO.setDescription("New description");

        // Mock the behavior of the repository
        when(productRepository.save(any())).thenAnswer(invocation -> {
            Product savedProduct = invocation.getArgument(0);
            savedProduct.setId(1L);  // Set a mock ID for the saved product
            return savedProduct;
        });

        // Mock the behavior of the mapper if needed
        when(productMapper.ProductDTOToProduct(any())).thenAnswer(invocation -> {
            ProductDTO inputDTO = invocation.getArgument(0);
            Product mappedProduct = new Product();
            mappedProduct.setName(inputDTO.getName());
            mappedProduct.setPrice(inputDTO.getPrice());
            mappedProduct.setDescription(inputDTO.getDescription());
            return mappedProduct;
        });

        // Call the service method
        ProductDTO createdProduct = productService.createProduct(newProductDTO);

        // Assert the results
        assertNotNull(createdProduct);
        assertEquals(newProductDTO.getName(), createdProduct.getName());
        assertEquals(newProductDTO.getPrice(), createdProduct.getPrice());
        assertEquals(newProductDTO.getDescription(), createdProduct.getDescription());

        // Verify that the repository method was called
        verify(productRepository, times(1)).save(any());
    }
}