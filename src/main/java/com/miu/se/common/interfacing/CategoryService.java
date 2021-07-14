package com.miu.se.common.interfacing;

import com.miu.se.common.entity.Category;
import com.miu.se.common.entity.Product;

import java.util.List;

public interface CategoryService {
    public Category save(Category category);

    public List<Category> getAll();

    public void deleteById(Long Id);

    public Product addProduct(Long id, Product product);

    public List<Product> getProducts(Long categoryId);
}
