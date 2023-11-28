package com.products.springProducts.mapper;

import com.products.springProducts.model.Product;
import com.products.springProducts.model.ProductDTO;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@Slf4j
public class ProductMapper implements IProductMapper {
    @Override
    public Product ProductDTOToProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        log.info("Converting ProductDTO to Product for name {}", productDTO.getName());
        Product product = new Product();

        if (productDTO.getId() != null) {
            product.setId(productDTO.getId());
        }
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }

    @Override
    public ProductDTO ProductToProductDTOProduct(Product product) {
        if (product == null) {
            return null;
        }

        log.info("Converting ProductDTO to Product for id {}", product.getId());
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());

        return productDTO;
    }
}
