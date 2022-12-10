package com.inventory.inventory.businesslayer.services;

import com.inventory.inventory.businesslayer.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ProductService {
    void addProduct(String name, String description, Boolean isAvailable, BigDecimal price);
    Product getProduct();
}
