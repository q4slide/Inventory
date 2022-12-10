package com.inventory.inventory.businesslayer.implementation;

import com.inventory.inventory.businesslayer.entity.Product;
import com.inventory.inventory.businesslayer.services.ProductService;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author TomaVasilev
 * @version 1.0
 */
public class ProductServiceImpl implements ProductService {
    private Product product;


    public void addProduct(String name, String description, Boolean isAvailable, BigDecimal price) {
        product = new Product(name,description,isAvailable,price,LocalDate.now());
    }

    public Product getProduct() {
        return product;
    }

}
