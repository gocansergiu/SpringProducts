package com.products.springProducts;

import com.products.springProducts.controller.ProductController;
import com.products.springProducts.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringProductsApplicationTests {
    @Autowired
    private ProductController productController;
    @Autowired
    private IProductService productService;

    @Test
    public void contextLoads() {
        assertThat(productController).isNotNull();
        assertThat(productService).isNotNull();
    }

}
