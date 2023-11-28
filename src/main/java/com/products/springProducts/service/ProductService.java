package com.products.springProducts.service;

import com.products.springProducts.exception.ProductNotFoundException;
import com.products.springProducts.model.Product;
import com.products.springProducts.model.ProductDTO;
import com.products.springProducts.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.products.springProducts.mapper.IProductMapper.PRODUCT_MAPPER;

@SuppressWarnings("ALL")
@Service
@Transactional
@Slf4j
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    private Product getProductByName(String name) {
        log.info("ProductService: Fetching Product with name {}", name);
        return productRepository.findByName(name).orElseThrow(ProductNotFoundException::new);
    }

    private Product getProductById(Long id) {
        log.info("ProductService: Fetching Product with id {}", id);
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }


    public ProductDTO getProductDTOById(Long id) {
        Product product = getProductById(id);
        log.info("ProductService: Product found for {}", id);
        return PRODUCT_MAPPER.ProductToProductDTOProduct(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        log.info("ProductService: Product list returned.");
        return products
                .stream()
                .map(PRODUCT_MAPPER::ProductToProductDTOProduct)
                .collect(Collectors.toList());
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        log.info("ProductService: Trying to get Product id for {}.", productDTO.getId());
        Product existingProduct = getProductById(productDTO.getId());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setName(productDTO.getName());
        log.info("ProductService: Saving newly changes.");
        return PRODUCT_MAPPER.ProductToProductDTOProduct(productRepository.save(existingProduct));
    }

    public ProductDTO changePrice(ProductDTO productDTO) {
        log.info("ProductService: Trying to get Product id for {}.", productDTO.getId());
        Product existingProduct = getProductById(productDTO.getId());
        existingProduct.setPrice(productDTO.getPrice());
        log.info("ProductService: Saving newly changes.");
        return PRODUCT_MAPPER.ProductToProductDTOProduct(productRepository.save(existingProduct));
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = PRODUCT_MAPPER.ProductDTOToProduct(productDTO);
        log.info("ProductService: Saving changes.");
        Product productSaved = productRepository.save(product);
        return PRODUCT_MAPPER.ProductToProductDTOProduct(productSaved);
    }
}
