package com.products.springProducts.service;

import com.products.springProducts.model.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO getProductDTOById(Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO updateProduct(ProductDTO productDTO);

    ProductDTO changePrice(ProductDTO productDTO);

    ProductDTO createProduct(ProductDTO productDTO);

    void deleteById(Long id);

}
