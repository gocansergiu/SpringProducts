package com.products.springProducts.service;

import com.products.springProducts.exception.ProductNotFoundException;
import com.products.springProducts.model.Product;
import com.products.springProducts.model.ProductDTO;
import com.products.springProducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.products.springProducts.mapper.IProductMapper.PRODUCT_MAPPER;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Autowired
    private ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private Product getProductByName(String name) {
        return productRepository.findByName(name).orElseThrow(ProductNotFoundException::new);
    }

    private Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }


    public ProductDTO getProductDTOById(Long id) {
        Product product = getProductById(id);
        return PRODUCT_MAPPER.ProductToProductDTOProduct(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(PRODUCT_MAPPER::ProductToProductDTOProduct)
                .collect(Collectors.toList());
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product existingProduct = getProductById(productDTO.getId());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setName(productDTO.getName());
        return PRODUCT_MAPPER.ProductToProductDTOProduct(productRepository.save(existingProduct));
    }

    public ProductDTO changePrice(ProductDTO productDTO) {
        Product existingProduct = getProductById(productDTO.getId());
        existingProduct.setPrice(productDTO.getPrice());
        return PRODUCT_MAPPER.ProductToProductDTOProduct(productRepository.save(existingProduct));
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = PRODUCT_MAPPER.ProductDTOToProduct(productDTO);
        Product productSaved = productRepository.save(product);
        return PRODUCT_MAPPER.ProductToProductDTOProduct(productSaved);
    }
}
