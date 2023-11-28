package com.products.springProducts.controller;

import com.products.springProducts.exception.ProductNotAcceptableException;
import com.products.springProducts.exception.ProductNotFoundException;
import com.products.springProducts.model.ProductDTO;
import com.products.springProducts.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductDTOById(id));
    }

    @DeleteMapping(value = "admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping(value = "admin/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        if (productDTO.getId() != null) {
            throw new ProductNotAcceptableException();
        }
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PutMapping(value = "admin/modify")
    public ResponseEntity<ProductDTO> modifyProduct(@RequestBody ProductDTO productDTO) {
        if (productDTO.getId() == null) {
            throw new ProductNotFoundException();
        }
        return ResponseEntity.ok(productService.updateProduct(productDTO));
    }

    @PutMapping(value = "admin/modify-price")
    public ResponseEntity<ProductDTO> modifyPrice(@RequestBody ProductDTO productDTO) {
        if (productDTO.getId() == null) {
            throw new ProductNotFoundException();
        }
        return ResponseEntity.ok(productService.changePrice(productDTO));
    }


}
