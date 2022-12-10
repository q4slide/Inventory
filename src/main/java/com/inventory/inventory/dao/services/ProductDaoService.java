package com.inventory.inventory.dao.services;

import com.inventory.inventory.businesslayer.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDaoService {
    void addProduct(Product product);

    void removeProduct(Product product);

    List<Product> productList();

    List<Product> onlyLtta();
    void updateProduct(Product product);
}
