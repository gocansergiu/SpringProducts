package com.products.springProducts.mapper;

import com.products.springProducts.model.Product;
import com.products.springProducts.model.ProductDTO;

public class ProductMapper implements IProductMapper {
    @Override
    public Product ProductDTOToProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

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

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());

        return productDTO;
    }
}
