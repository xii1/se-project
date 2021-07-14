package com.miu.se.common.interfacing;

import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.Review;

import java.util.List;

public interface ProductService {
    public List<Product> getAll();

    public Product save(Product product);

    public void deleteById(Long id);

    public Review addReview(Long id, Review review);
}
