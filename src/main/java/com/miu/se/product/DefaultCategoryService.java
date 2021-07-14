package com.miu.se.product;

import com.miu.se.common.entity.Category;
import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.User;
import com.miu.se.common.interfacing.CategoryService;
import com.miu.se.common.interfacing.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class DefaultCategoryService implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Product addProduct(Long id, Product product) {
        Long userId = 0L;
        product.setCreatedBy(new User(userId));
        Category category = categoryRepository.getOne(id);
        Product savedProduct = productService.save(product);
        if(category.getProducts() == null) {
            category.setProducts(Arrays.asList(savedProduct));
        } else {
            category.getProducts().add(savedProduct);
        }
        categoryRepository.save(category);
        return savedProduct;
    }

    @Override
    public List<Product> getProducts(Long categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        return category.getProducts();
    }
}
