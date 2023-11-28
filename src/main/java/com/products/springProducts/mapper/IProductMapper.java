package com.products.springProducts.mapper;

import com.products.springProducts.model.Product;
import com.products.springProducts.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IProductMapper {
    IProductMapper PRODUCT_MAPPER = Mappers.getMapper(IProductMapper.class);

    Product ProductDTOToProduct(ProductDTO productDTO);

    ProductDTO ProductToProductDTOProduct(Product product);
}
